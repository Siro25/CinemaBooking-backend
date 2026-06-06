package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.PaymentRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.PaymentResponse;
import com.sidocinemas.cinema_booking.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Customer Payment Controller - Chỉ cho customer
 * Endpoint: /api/v1/customer/payments
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/customer/payments")
@RequiredArgsConstructor
public class CustomerPaymentController {

    private final PaymentService paymentService;

    /**
     * Thanh toán cho booking
     */
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        // TODO: Kiểm tra booking có thuộc về current user không
        log.info("Customer processing payment for bookingId={}", request.getBookingId());
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.createPayment(request))
                .message("Thanh toán thành công")
                .build();
    }

    /**
     * Lịch sử thanh toán của mình
     */
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<List<PaymentResponse>> getMyPayments() {
        // TODO: Filter payments của current user
        log.info("Customer getting payment history");
        return ApiResponse.<List<PaymentResponse>>builder()
                .data(paymentService.getAllPayments()) // Tạm thời get all
                .build();
    }

    /**
     * Xem chi tiết thanh toán
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<PaymentResponse> getPaymentById(@PathVariable Long id) {
        // TODO: Kiểm tra payment có thuộc về current user không
        log.info("Customer getting payment id={}", id);
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.getPaymentById(id))
                .build();
    }
}