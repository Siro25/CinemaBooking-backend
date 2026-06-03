package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.*;
import com.sidocinemas.cinema_booking.dto.request.BookingRequest;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.dto.response.TicketResponse;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import com.sidocinemas.cinema_booking.enums.SeatType;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.*;
import com.sidocinemas.cinema_booking.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;
    ShowtimeRepository showtimeRepository;
    SeatRepository seatRepository;
    TicketRepository ticketRepository;
    UserRepository userRepository;

    // ── Hệ số giá theo loại ghế ─────────────────────────────────────────────
    private static final BigDecimal VIP_MULTIPLIER    = new BigDecimal("1.5");
    private static final BigDecimal COUPLE_MULTIPLIER = new BigDecimal("2.0");

    @Override
    @Transactional
    public BookingResponse createBooking(Long customerId, BookingRequest request) {
        if (request.getSeatIds() == null || request.getSeatIds().isEmpty()) {
            throw new AppException(ErrorCode.NO_SEATS_SELECTED);
        }

        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));

        Long roomId = showtime.getRoom().getId();

        // 1. Validate tất cả ghế tồn tại và thuộc đúng phòng
        List<Seat> seats = new ArrayList<>();
        for (Long seatId : request.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
            if (!seat.getRoom().getId().equals(roomId)) {
                throw new AppException(ErrorCode.SEAT_NOT_BELONG_TO_ROOM);
            }
            seats.add(seat);
        }

        // 2. Kiểm tra ghế chưa bị đặt (race-condition safe với @Transactional)
        boolean anyBooked = ticketRepository.existsBookedSeats(request.getShowtimeId(), request.getSeatIds());
        if (anyBooked) {
            throw new AppException(ErrorCode.SEAT_ALREADY_BOOKED);
        }

        // 3. Tính tổng tiền
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<Ticket> tickets = new ArrayList<>();

        for (Seat seat : seats) {
            BigDecimal ticketPrice = calculatePrice(showtime.getBasePrice(), seat.getType());
            totalPrice = totalPrice.add(ticketPrice);

            Ticket ticket = Ticket.builder()
                    .seat(seat)
                    .price(ticketPrice)
                    .qrCode(generateQrCode())
                    .build();
            tickets.add(ticket);
        }

        // 4. Tạo booking
        Booking booking = Booking.builder()
                .customer(customer)
                .showtime(showtime)
                .status(BookingStatus.HOLD)
                .totalPrice(totalPrice)
                .tickets(new ArrayList<>())
                .build();

        // Gắn booking vào ticket (cascade save)
        for (Ticket ticket : tickets) {
            ticket.setBooking(booking);
            booking.getTickets().add(ticket);
        }

        booking = bookingRepository.save(booking);
        log.info("Created booking id={} for customerId={}, showtimeId={}, seats={}",
                booking.getId(), customerId, request.getShowtimeId(), request.getSeatIds());

        return mapToResponse(booking);
    }

    @Override
    @Transactional
    public BookingResponse confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            throw new AppException(ErrorCode.BOOKING_ALREADY_CONFIRMED);
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_CANCEL);
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        booking = bookingRepository.save(booking);
        log.info("Confirmed booking id={}", bookingId);
        return mapToResponse(booking);
    }

    @Override
    @Transactional
    public BookingResponse cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new AppException(ErrorCode.BOOKING_CANNOT_CANCEL);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking = bookingRepository.save(booking);
        log.info("Cancelled booking id={}", bookingId);
        return mapToResponse(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return mapToResponse(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingsByCustomer(Long customerId) {
        if (!userRepository.existsById(customerId)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return bookingRepository.findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    /**
     * Tính giá vé dựa theo loại ghế:
     * VIP = basePrice * 1.5, COUPLE = basePrice * 2.0, STANDARD = basePrice
     */
    private BigDecimal calculatePrice(BigDecimal basePrice, SeatType seatType) {
        return switch (seatType) {
            case VIP    -> basePrice.multiply(VIP_MULTIPLIER);
            case COUPLE -> basePrice.multiply(COUPLE_MULTIPLIER);
            default     -> basePrice;
        };
    }

    /** Sinh QR code giả — production thay bằng thư viện thực (ZXing,...) */
    private String generateQrCode() {
        return "QR-" + UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    private BookingResponse mapToResponse(Booking booking) {
        Showtime showtime = booking.getShowtime();
        User customer    = booking.getCustomer();
        Room room        = showtime != null ? showtime.getRoom() : null;
        Movie movie      = showtime != null ? showtime.getMovie() : null;
        Cinema cinema    = room     != null ? room.getCinema()    : null;

        List<TicketResponse> ticketResponses = booking.getTickets().stream()
                .map(t -> {
                    Seat seat = t.getSeat();
                    return TicketResponse.builder()
                            .id(t.getId())
                            .seatId(seat != null ? seat.getId() : null)
                            .seatRow(seat != null ? seat.getRow() : null)
                            .seatNumber(seat != null ? seat.getNumber() : null)
                            .seatType(seat != null ? seat.getType() : null)
                            .price(t.getPrice())
                            .qrCode(t.getQrCode())
                            .build();
                })
                .toList();

        return BookingResponse.builder()
                .id(booking.getId())
                .customerId(customer != null ? customer.getId() : null)
                .customerName(customer != null ? customer.getFullName() : null)
                .customerEmail(customer != null ? customer.getEmail() : null)
                .showtimeId(showtime != null ? showtime.getId() : null)
                .movieTitle(movie != null ? movie.getTitle() : null)
                .startTime(showtime != null ? showtime.getStartTime() : null)
                .endTime(showtime != null ? showtime.getEndTime() : null)
                .roomNumber(room != null ? room.getRoomNumber() : null)
                .cinemaName(cinema != null ? cinema.getName() : null)
                .status(booking.getStatus())
                .totalPrice(booking.getTotalPrice())
                .createdAt(booking.getCreatedAt())
                .tickets(ticketResponses)
                .build();
    }
}
