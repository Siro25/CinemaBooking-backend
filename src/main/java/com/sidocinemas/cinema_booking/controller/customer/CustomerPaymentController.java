package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.PaymentRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.dto.response.PaymentResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.service.BookingService;
import com.sidocinemas.cinema_booking.service.PaymentService;
import com.sidocinemas.cinema_booking.util.SecurityUtils;
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
    private final BookingService bookingService;

    /**
     * Thanh toán cho booking
     * Kiểm tra booking thuộc về customer hiện tại trước khi thanh toán
     */
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PaymentResponse> processPayment(@Valid @RequestBody PaymentRequest request) {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Processing payment for bookingId={}, customerId={}", request.getBookingId(), customerId);

        // Kiểm tra booking thuộc về customer này
        BookingResponse booking = bookingService.getBookingById(request.getBookingId());
        if (!customerId.equals(booking.getCustomerId())) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        PaymentResponse response = paymentService.createPayment(request);
        // Tự động confirm booking khi thanh toán thành công
        bookingService.confirmBooking(request.getBookingId());
        return ApiResponse.<PaymentResponse>builder()
                .data(response)
                .message("Thanh toán thành công")
                .build();
    }

    /**
     * Xác nhận thanh toán qua VietQR Sandbox
     * Kiểm tra booking thuộc về customer hiện tại
     */
    @PostMapping("/{bookingId}/confirm-vietqr")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<PaymentResponse> confirmVietQR(@PathVariable Long bookingId) {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Confirming VietQR payment for bookingId={}, customerId={}", bookingId, customerId);

        // Kiểm tra booking thuộc về customer này
        BookingResponse booking = bookingService.getBookingById(bookingId);
        if (!customerId.equals(booking.getCustomerId())) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        PaymentRequest request = PaymentRequest.builder()
                .bookingId(bookingId)
                .amount(booking.getTotalPrice())
                .method(com.sidocinemas.cinema_booking.enums.PaymentMethod.CARD)
                .build();

        PaymentResponse response = paymentService.createPayment(request);
        bookingService.confirmBooking(bookingId);

        return ApiResponse.<PaymentResponse>builder()
                .data(response)
                .message("Thanh toán VietQR Sandbox thành công")
                .build();
    }

    /**
     * Lịch sử thanh toán của mình (chỉ trả về payment của customer hiện tại)
     */
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<List<PaymentResponse>> getMyPayments() {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Getting payment history for customerId={}", customerId);
        return ApiResponse.<List<PaymentResponse>>builder()
                .data(paymentService.getPaymentsByCustomer(customerId))
                .build();
    }

    /**
     * Xem chi tiết thanh toán (chỉ của chính mình)
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<PaymentResponse> getPaymentById(@PathVariable Long id) {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Getting payment id={}, customerId={}", id, customerId);
        return ApiResponse.<PaymentResponse>builder()
                .data(paymentService.getMyPaymentById(id, customerId))
                .build();
    }
}