package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Booking;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import com.sidocinemas.cinema_booking.enums.PaymentStatus;
import com.sidocinemas.cinema_booking.repository.BookingRepository;
import com.sidocinemas.cinema_booking.repository.PaymentRepository;
import com.sidocinemas.cinema_booking.service.SeatHoldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Job định kỳ dọn dẹp các booking HOLD đã hết hạn TTL.
 *
 * Chiến lược tối ưu:
 * 1. Fetch expired bookings với JOIN FETCH tickets+seats (cần để lấy seat IDs
 * cho Redis).
 * 2. Giải phóng Redis cho từng booking (O(n) nhưng cần thiết).
 * 3. Bulk UPDATE bookings CANCELLED — 1 câu SQL thay vì N.
 * 4. Bulk UPDATE payments FAILED — 1 câu SQL thay vì N.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookingCleanupService {

        private final BookingRepository bookingRepository;
        private final PaymentRepository paymentRepository;
        private final SeatHoldService seatHoldService;

        @Value("${seat-hold.ttl-minutes:10}")
        private long ttlMinutes;

        // fixedDelay: chờ lần trước kết thúc mới đếm 1 phút tiếp
        @Scheduled(fixedDelayString = "PT1M")
        @Transactional
        public void cleanupExpiredHoldBookings() {
                LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(ttlMinutes);

                // Bước 1: Lấy expired bookings với JOIN FETCH để load tickets+seats sẵn (tránh
                // N+1)
                List<Booking> expiredBookings = bookingRepository
                                .findExpiredHoldBookings(expirationTime, BookingStatus.HOLD);

                if (expiredBookings.isEmpty())
                        return;

                log.info("[BookingCleanup] Found {} expired HOLD booking(s) to cancel.", expiredBookings.size());

                // Bước 2: Giải phóng Redis cho mỗi booking (phòng trường hợp Redis TTL chưa
                // expire)
                for (Booking booking : expiredBookings) {
                        List<Long> seatIds = booking.getTickets().stream()
                                        .map(t -> t.getSeat().getId())
                                        .toList();
                        seatHoldService.releaseSeats(booking.getShowtime().getId(), seatIds);
                }

                // Bước 3: Bulk UPDATE bookings HOLD → CANCELLED (1 SQL thay vì N save())
                int cancelledCount = bookingRepository.bulkCancelExpiredHoldBookings(
                                expirationTime, BookingStatus.HOLD, BookingStatus.CANCELLED);

                // Bước 4: Bulk UPDATE payments PENDING → FAILED (1 SQL thay vì N save())
                List<Long> bookingIds = expiredBookings.stream().map(Booking::getId).toList();
                int failedPayments = paymentRepository.bulkFailPendingPayments(
                                bookingIds, PaymentStatus.FAILED, PaymentStatus.PENDING);

                log.info("[BookingCleanup] Done. Cancelled {} booking(s), failed {} payment(s).",
                                cancelledCount, failedPayments);
        }
}
