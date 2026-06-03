package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.BookingRequest;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import java.util.List;

public interface BookingService {
    /** Customer tạo đặt vé mới (status = HOLD) */
    BookingResponse createBooking(Long customerId, BookingRequest request);

    /**
     * Xác nhận đặt vé (chuyển HOLD → CONFIRMED) — gọi sau khi thanh toán thành công
     */
    BookingResponse confirmBooking(Long bookingId);

    /** Huỷ đặt vé (HOLD/CONFIRMED → CANCELLED) */
    BookingResponse cancelBooking(Long bookingId);

    /** Lấy chi tiết một booking */
    BookingResponse getBookingById(Long id);

    /** Lấy lịch sử đặt vé của customer */
    List<BookingResponse> getBookingsByCustomer(Long customerId);

    /** ADMIN: Lấy tất cả booking (có thể filter theo status) */
    List<BookingResponse> getAllBookings();
}