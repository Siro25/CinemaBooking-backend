package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Payment;
import com.sidocinemas.cinema_booking.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

       Optional<Payment> findByBookingId(Long bookingId);

       List<Payment> findByBooking_Customer_IdOrderByCreatedAtDesc(Long customerId);

       Optional<Payment> findByIdAndBooking_Customer_Id(Long id, Long customerId);

       /**
        * Bulk UPDATE: Chuyển tất cả payment PENDING của các booking đã cancel →
        * FAILED.
        */
       @Modifying
       @Query("UPDATE Payment p SET p.status = :failed " +
                     "WHERE p.booking.id IN :bookingIds AND p.status = :pending")
       int bulkFailPendingPayments(@Param("bookingIds") List<Long> bookingIds,
                     @Param("failed") PaymentStatus failed,
                     @Param("pending") PaymentStatus pending);
}
