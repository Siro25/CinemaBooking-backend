package com.sidocinemas.cinema_booking.service;

import java.util.List;

/**
 * Service xử lý việc tạm giữ ghế (Seat Hold) bằng Redis TTL.
 * Key pattern: seat:hold:{showtimeId}:{seatId} → value: userId
 */
public interface SeatHoldService {

    /**
     * Giữ danh sách ghế cho user trong showtimeId.
     * TTL được lấy từ config seat-hold.ttl-minutes.
     * Ném IllegalStateException nếu ghế đang bị người khác giữ.
     */
    void holdSeats(Long showtimeId, List<Long> seatIds, Long userId);

    /**
     * Giải phóng danh sách ghế khỏi Redis.
     */
    void releaseSeats(Long showtimeId, List<Long> seatIds);

    /**
     * Kiểm tra xem có ghế nào trong danh sách đang bị người khác (khác userId) giữ không.
     */
    boolean isAnyHeldByOther(Long showtimeId, List<Long> seatIds, Long userId);

    /**
     * Kiểm tra xem hold của user còn hiệu lực không (ít nhất 1 ghế còn key trong Redis).
     */
    boolean isHoldStillValid(Long showtimeId, List<Long> seatIds, Long userId);
}
