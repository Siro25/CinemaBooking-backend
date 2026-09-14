package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.*;
import com.sidocinemas.cinema_booking.dto.request.BookingComboRequest;
import com.sidocinemas.cinema_booking.dto.request.ComboItemRequest;
import com.sidocinemas.cinema_booking.dto.response.*;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.*;
import com.sidocinemas.cinema_booking.service.ComboService;
import com.sidocinemas.cinema_booking.util.QrCodeUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ComboServiceImpl implements ComboService {

    ComboItemRepository comboItemRepository;
    BookingComboRepository bookingComboRepository;
    BookingRepository bookingRepository;
    CinemaRepository cinemaRepository;
    TicketRepository ticketRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ComboItemResponse> getAvailableComboByCinema(Long cinemaId) {
        return comboItemRepository.findByCinemaIdAndIsAvailableTrue(cinemaId)
                .stream()
                .map(this::mapToComboItemResponse)
                .toList();
    }

    @Override
    @Transactional
    public BookingResponse updateBookingCombos(Long bookingId, Long customerId, BookingComboRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        if (!booking.getCustomer().getId().equals(customerId)) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        if (booking.getStatus() != BookingStatus.HOLD) {
            throw new AppException(ErrorCode.BOOKING_ALREADY_CONFIRMED);
        }

        Long cinemaId = booking.getShowtime().getRoom().getCinema().getId();

        bookingComboRepository.deleteByBookingId(bookingId);
        booking.getCombos().clear();

        BigDecimal comboTotal = BigDecimal.ZERO;

        if (request != null && request.getItems() != null) {
            for (BookingComboRequest.ComboSelection sel : request.getItems()) {
                if (sel.getQuantity() == null || sel.getQuantity() <= 0)
                    continue;

                ComboItem comboItem = comboItemRepository.findById(sel.getComboItemId())
                        .orElseThrow(() -> new AppException(ErrorCode.COMBO_NOT_FOUND));

                if (!Boolean.TRUE.equals(comboItem.getIsAvailable())) {
                    throw new AppException(ErrorCode.COMBO_NOT_AVAILABLE);
                }

                if (!comboItem.getCinema().getId().equals(cinemaId)) {
                    throw new AppException(ErrorCode.COMBO_NOT_BELONG_TO_CINEMA);
                }

                BigDecimal subtotal = comboItem.getPrice().multiply(BigDecimal.valueOf(sel.getQuantity()));
                comboTotal = comboTotal.add(subtotal);

                BookingCombo bc = BookingCombo.builder()
                        .booking(booking)
                        .comboItem(comboItem)
                        .quantity(sel.getQuantity())
                        .subtotal(subtotal)
                        .build();
                booking.getCombos().add(bc);
            }
        }

        BigDecimal ticketTotal = booking.getTickets().stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        booking.setTotalPrice(ticketTotal.add(comboTotal));

        booking = bookingRepository.save(booking);
        log.info("[COMBO] Updated combos for bookingId={}, comboTotal={}, newTotal={}",
                bookingId, comboTotal, booking.getTotalPrice());

        return mapToBookingResponse(booking);
    }

    // ── Manager CRUD ────────────────────────────────────────────────────────

    @Override
    @Transactional(readOnly = true)
    public List<ComboItemResponse> getAllComboByCinema(Long cinemaId) {
        return comboItemRepository.findByCinemaId(cinemaId)
                .stream()
                .map(this::mapToComboItemResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ComboItemResponse getComboById(Long id) {
        ComboItem item = comboItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMBO_NOT_FOUND));
        return mapToComboItemResponse(item);
    }

    @Override
    @Transactional
    public ComboItemResponse createCombo(Long cinemaId, ComboItemRequest request) {
        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));

        ComboItem item = ComboItem.builder()
                .cinema(cinema)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .isAvailable(request.getIsAvailable() != null ? request.getIsAvailable() : true)
                .build();

        item = comboItemRepository.save(item);
        log.info("[MANAGER] Created combo id={} for cinemaId={}", item.getId(), cinemaId);
        return mapToComboItemResponse(item);
    }

    @Override
    @Transactional
    public ComboItemResponse updateCombo(Long id, Long cinemaId, ComboItemRequest request) {
        ComboItem item = comboItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMBO_NOT_FOUND));

        // Đảm bảo combo thuộc rạp của manager này
        if (!item.getCinema().getId().equals(cinemaId)) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setImageUrl(request.getImageUrl());
        if (request.getIsAvailable() != null) {
            item.setIsAvailable(request.getIsAvailable());
        }

        item = comboItemRepository.save(item);
        log.info("[MANAGER] Updated combo id={}", id);
        return mapToComboItemResponse(item);
    }

    @Override
    @Transactional
    public void deleteCombo(Long id, Long cinemaId) {
        ComboItem item = comboItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMBO_NOT_FOUND));

        if (!item.getCinema().getId().equals(cinemaId)) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        comboItemRepository.delete(item);
        log.info("[MANAGER] Deleted combo id={}", id);
    }

    // ── Helpers ─────────────────────────────────────────────────────────────

    private ComboItemResponse mapToComboItemResponse(ComboItem item) {
        return ComboItemResponse.builder()
                .id(item.getId())
                .cinemaId(item.getCinema() != null ? item.getCinema().getId() : null)
                .cinemaName(item.getCinema() != null ? item.getCinema().getName() : null)
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .imageUrl(item.getImageUrl())
                .isAvailable(item.getIsAvailable())
                .build();
    }

    private BookingResponse mapToBookingResponse(Booking booking) {
        Showtime showtime = booking.getShowtime();
        User customer = booking.getCustomer();
        Room room = showtime != null ? showtime.getRoom() : null;
        Movie movie = showtime != null ? showtime.getMovie() : null;
        Cinema cinema = room != null ? room.getCinema() : null;

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
                            .qrCodeBase64(QrCodeUtil.generateQrCodeBase64(t.getQrCode(), 250, 250))
                            .build();
                })
                .toList();

        List<BookingComboResponse> comboResponses = booking.getCombos().stream()
                .map(bc -> {
                    ComboItem ci = bc.getComboItem();
                    return BookingComboResponse.builder()
                            .id(bc.getId())
                            .comboItemId(ci != null ? ci.getId() : null)
                            .comboName(ci != null ? ci.getName() : null)
                            .comboDescription(ci != null ? ci.getDescription() : null)
                            .comboImageUrl(ci != null ? ci.getImageUrl() : null)
                            .unitPrice(ci != null ? ci.getPrice() : null)
                            .quantity(bc.getQuantity())
                            .subtotal(bc.getSubtotal())
                            .build();
                })
                .toList();

        BigDecimal comboPrice = booking.getCombos().stream()
                .map(BookingCombo::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

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
                .comboPrice(comboPrice)
                .createdAt(booking.getCreatedAt())
                .tickets(ticketResponses)
                .combos(comboResponses)
                .build();
    }
}
