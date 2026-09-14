package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.PaymentRequest;
import com.sidocinemas.cinema_booking.dto.response.PaymentResponse;
import com.sidocinemas.cinema_booking.enums.PaymentStatus;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse updatePaymentStatus(Long id, PaymentStatus status);

    PaymentResponse getPaymentById(Long id);

    PaymentResponse getPaymentByBookingId(Long bookingId);

    List<PaymentResponse> getAllPayments();

    List<PaymentResponse> getPaymentsByCustomer(Long customerId);

    PaymentResponse getMyPaymentById(Long id, Long customerId);
}