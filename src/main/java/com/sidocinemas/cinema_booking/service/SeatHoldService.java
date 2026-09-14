package com.sidocinemas.cinema_booking.service;

import java.util.List;

/**
 * Service xử lý việc tạm giữ ghế (Seat Hold) bằng Redis TTL.
 * Key pattern: seat:hold:{showtimeId}:{seatId} → value: userId
 */
public interface SeatHoldService {

    void holdSeats(Long showtimeId, List<Long> seatIds, Long userId);

    void releaseSeats(Long showtimeId, List<Long> seatIds);

    boolean isAnyHeldByOther(Long showtimeId, List<Long> seatIds, Long userId);

    boolean isHoldStillValid(Long showtimeId, List<Long> seatIds, Long userId);
}
