package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.BookingRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Customer Booking Controller - Chỉ cho customer
 * Endpoint: /api/v1/customer/bookings
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/customer/bookings")
@RequiredArgsConstructor
public class CustomerBookingController {

    private final BookingService bookingService;

    /**
     * Tạo đặt vé mới
     */
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        // TODO: Lấy customerId từ JWT token trong SecurityContext
        // Tạm thời hardcode customerId = 1 để test, cần implement JWT service
        Long customerId = 1L; // Sẽ được thay thế bằng getCurrentUserId()
        log.info("Customer creating booking for showtimeId={}", request.getShowtimeId());
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.createBooking(customerId, request))
                .message("Đặt vé thành công, vui lòng thanh toán để xác nhận")
                .build();
    }

    /**
     * Huỷ booking của chính mình
     */
    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<BookingResponse> cancelBooking(@PathVariable Long id) {
        // TODO: Kiểm tra booking có thuộc về current user không
        log.info("Customer cancelling booking id={}", id);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.cancelBooking(id))
                .message("Huỷ đặt vé thành công")
                .build();
    }

    /**
     * Lấy chi tiết booking của mình
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<BookingResponse> getBookingById(@PathVariable Long id) {
        // TODO: Kiểm tra booking có thuộc về current user không
        log.info("Customer getting booking id={}", id);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.getBookingById(id))
                .build();
    }

    /**
     * Lịch sử đặt vé của mình
     */
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<List<BookingResponse>> getMyBookings() {
        // TODO: Lấy customerId từ JWT và filter bookings
        Long customerId = 1L; // Tạm thời hardcode
        log.info("Customer getting booking history");
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getBookingsByCustomer(customerId))
                .build();
    }
}