package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    List<Payment> findByBooking_Customer_IdOrderByCreatedAtDesc(Long customerId);

    Optional<Payment> findByIdAndBooking_Customer_Id(Long id, Long customerId);
}
