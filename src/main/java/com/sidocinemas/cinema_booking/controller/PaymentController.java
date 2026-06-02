package com.sidocinemas.cinema_booking.controller;

import com.sidocinemas.cinema_booking.dto.request.PaymentRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.PaymentResponse;
import com.sidocinemas.cinema_booking.enums.PaymentStatus;
import com.sidocinemas.cinema_booking.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {

    PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest request) {
        log.info("Creating payment for request: {}", request);
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.createPayment(request))
                .message("Tạo thanh toán thành công")
                .build();
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<PaymentResponse> updatePaymentStatus(@PathVariable Long id,
            @RequestParam PaymentStatus status) {
        log.info("Updating payment id={} to status={}", id, status);
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.updatePaymentStatus(id, status))
                .message("Cập nhật trạng thái thanh toán thành công")
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<PaymentResponse> getPaymentById(@PathVariable Long id) {
        log.info("Getting payment by id={}", id);
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.getPaymentById(id))
                .build();
    }

    @GetMapping("/booking/{bookingId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<PaymentResponse> getPaymentByBookingId(@PathVariable Long bookingId) {
        log.info("Getting payment by bookingId={}", bookingId);
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.getPaymentByBookingId(bookingId))
                .build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<PaymentResponse>> getAllPayments() {
        log.info("Getting all payments");
        return ApiResponse.<List<PaymentResponse>>builder()
                .data(paymentService.getAllPayments())
                .build();
    }
}