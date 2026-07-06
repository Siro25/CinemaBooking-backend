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

    /** Lấy tất cả thanh toán của một customer (chỉ trả về của chính họ) */
    List<PaymentResponse> getPaymentsByCustomer(Long customerId);

    /** Lấy thanh toán theo id nhưng chỉ nếu thuộc về customer đó */
    PaymentResponse getMyPaymentById(Long id, Long customerId);
}