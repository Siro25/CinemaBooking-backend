package com.sidocinemas.cinema_booking.controller;

import com.sidocinemas.cinema_booking.dto.request.BookingRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    // ── CUSTOMER: Tạo đặt vé (customerId lấy từ JWT) ─────────────────────────
    @PostMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER') and #customerId == authentication.principal.id" +
            " or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(
            @PathVariable Long customerId,
            @Valid @RequestBody BookingRequest request) {
        log.info("Customer id={} creating booking for showtimeId={}", customerId, request.getShowtimeId());
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.createBooking(customerId, request))
                .message("Đặt vé thành công, vui lòng thanh toán để xác nhận")
                .build();
    }

    // ── Xác nhận booking (sau khi thanh toán) ────────────────────────────────
    @PatchMapping("/{id}/confirm")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<BookingResponse> confirmBooking(@PathVariable Long id) {
        log.info("Confirming booking id={}", id);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.confirmBooking(id))
                .message("Xác nhận đặt vé thành công")
                .build();
    }

    // ── Huỷ booking ──────────────────────────────────────────────────────────
    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<BookingResponse> cancelBooking(@PathVariable Long id) {
        log.info("Cancelling booking id={}", id);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.cancelBooking(id))
                .message("Huỷ đặt vé thành công")
                .build();
    }

    // ── Lấy chi tiết ─────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<BookingResponse> getBookingById(@PathVariable Long id) {
        log.info("Getting booking id={}", id);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.getBookingById(id))
                .build();
    }

    // ── Lịch sử đặt vé của một customer ──────────────────────────────────────
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CUSTOMER') and #customerId == authentication.principal.id)")
    public ApiResponse<List<BookingResponse>> getBookingsByCustomer(@PathVariable Long customerId) {
        log.info("Getting booking history for customerId={}", customerId);
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getBookingsByCustomer(customerId))
                .build();
    }

    // ── ADMIN: Tất cả bookings ────────────────────────────────────────────────
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<BookingResponse>> getAllBookings() {
        log.info("Admin fetching all bookings");
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getAllBookings())
                .build();
    }
}