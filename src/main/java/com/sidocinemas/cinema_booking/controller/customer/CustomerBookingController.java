package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.BookingRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.service.BookingService;
import com.sidocinemas.cinema_booking.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer/bookings")
@RequiredArgsConstructor
public class CustomerBookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Creating booking for showtimeId={}, customerId={}", request.getShowtimeId(), customerId);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.createBooking(customerId, request))
                .message("Đặt vé thành công, vui lòng thanh toán để xác nhận")
                .build();
    }

    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<BookingResponse> cancelBooking(@PathVariable Long id) {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Cancelling booking id={}, customerId={}", id, customerId);

        // Kiểm tra booking có thuộc về customer này không
        BookingResponse booking = bookingService.getBookingById(id);
        if (!customerId.equals(booking.getCustomerId())) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.cancelBooking(id))
                .message("Huỷ đặt vé thành công")
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<BookingResponse> getBookingById(@PathVariable Long id) {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Getting booking id={}, customerId={}", id, customerId);

        BookingResponse booking = bookingService.getBookingById(id);
        if (!customerId.equals(booking.getCustomerId())) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        return ApiResponse.<BookingResponse>builder()
                .data(booking)
                .build();
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<List<BookingResponse>> getMyBookings() {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Getting booking history for customerId={}", customerId);
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getBookingsByCustomer(customerId))
                .build();
    }
}