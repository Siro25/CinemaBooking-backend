package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Room;
import com.sidocinemas.cinema_booking.domain.Seat;
import com.sidocinemas.cinema_booking.dto.request.SeatRequest;
import com.sidocinemas.cinema_booking.dto.response.SeatResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.RoomRepository;
import com.sidocinemas.cinema_booking.repository.SeatRepository;
import com.sidocinemas.cinema_booking.repository.ShowtimeRepository;
import com.sidocinemas.cinema_booking.repository.TicketRepository;
import com.sidocinemas.cinema_booking.service.SeatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeatServiceImpl implements SeatService {
    SeatRepository seatRepository;
    RoomRepository roomRepository;
    ShowtimeRepository showtimeRepository;
    TicketRepository ticketRepository;

    @NonFinal
    @Value("${seat-hold.ttl-minutes:10}")
    long ttlMinutes;

    @Override
    @Transactional
    public SeatResponse createSeat(SeatRequest request) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        Seat seat = Seat.builder()
                .room(room)
                .row(request.getRow())
                .number(request.getNumber())
                .type(request.getType())
                .build();
        seat = seatRepository.save(seat);
        log.info("Created seat {} {} in roomId={}", seat.getRow(), seat.getNumber(), request.getRoomId());
        return mapToResponse(seat, null);
    }

    @Override
    @Transactional
    public SeatResponse updateSeat(Long id, SeatRequest request) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        seat.setRoom(room);
        seat.setRow(request.getRow());
        seat.setNumber(request.getNumber());
        seat.setType(request.getType());
        seat = seatRepository.save(seat);
        log.info("Updated seat id={}", id);
        return mapToResponse(seat, null);
    }

    @Override
    @Transactional
    public void deleteSeat(Long id) {
        if (!seatRepository.existsById(id)) {
            throw new AppException(ErrorCode.SEAT_NOT_FOUND);
        }
        seatRepository.deleteById(id);
        log.info("Deleted seat id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public SeatResponse getSeatById(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
        return mapToResponse(seat, null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponse> getSeatsByRoom(Long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new AppException(ErrorCode.ROOM_NOT_FOUND);
        }
        return seatRepository.findByRoomId(roomId).stream()
                .map(s -> mapToResponse(s, null))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponse> getSeatsByShowtime(Long showtimeId) {
        // Lấy showtime để biết roomId
        var showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        Long roomId = showtime.getRoom().getId();
        // Lấy tập các seat đã bị book
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(ttlMinutes);
        Set<Long> bookedSeatIds = ticketRepository
                .findBookedSeatsByShowtimeId(showtimeId, expirationTime)
                .stream()
                .map(com.sidocinemas.cinema_booking.domain.Seat::getId)
                .collect(Collectors.toSet());
        return seatRepository.findByRoomId(roomId).stream()
                .map(s -> mapToResponse(s, !bookedSeatIds.contains(s.getId())))
                .toList();
    }

    // ── Helper ──────────────────────────────────────────────────────────────
    private SeatResponse mapToResponse(Seat seat, Boolean available) {
        Room room = seat.getRoom();
        return SeatResponse.builder()
                .id(seat.getId())
                .roomId(room != null ? room.getId() : null)
                .roomNumber(room != null ? room.getRoomNumber() : null)
                .row(seat.getRow())
                .number(seat.getNumber())
                .type(seat.getType())
                .available(available)
                .build();
    }
}