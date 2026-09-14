package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Booking;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

       List<Booking> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

       List<Booking> findByStatus(BookingStatus status);

       /*
        * Lấy danh sách Booking HOLD đã hết hạn — dùng để lấy seat IDs trước khi giải
        * phóng Redis
        */
       @Query("SELECT b FROM Booking b JOIN FETCH b.tickets t JOIN FETCH t.seat " +
                     "WHERE b.status = :hold AND b.createdAt < :expirationTime")
       List<Booking> findExpiredHoldBookings(@Param("expirationTime") LocalDateTime expirationTime,
                     @Param("hold") BookingStatus hold);

       // Bulk UPDATE: Chuyển tất cả booking HOLD hết hạn → CANCELLED

       @Modifying
       @Query("UPDATE Booking b SET b.status = :cancelled " +
                     "WHERE b.status = :hold AND b.createdAt < :expirationTime")
       int bulkCancelExpiredHoldBookings(@Param("expirationTime") LocalDateTime expirationTime,
                     @Param("hold") BookingStatus hold,
                     @Param("cancelled") BookingStatus cancelled);

       @Query("SELECT b FROM Booking b " +
                     "JOIN b.showtime s JOIN s.room r JOIN r.cinema c " +
                     "WHERE c.id = :cinemaId " +
                     "ORDER BY b.createdAt DESC")
       List<Booking> findByCinemaIdOrderByCreatedAtDesc(@Param("cinemaId") Long cinemaId);

       @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM Booking b " +
                     "JOIN b.showtime s JOIN s.room r JOIN r.cinema c " +
                     "WHERE c.id = :cinemaId AND b.status = 'CONFIRMED'")
       BigDecimal sumRevenueByCinema(@Param("cinemaId") Long cinemaId);

       @Query("SELECT s.movie.title, COALESCE(SUM(b.totalPrice), 0), COUNT(b) " +
                     "FROM Booking b " +
                     "JOIN b.showtime s JOIN s.room r JOIN r.cinema c " +
                     "WHERE c.id = :cinemaId AND b.status = 'CONFIRMED' " +
                     "GROUP BY s.movie.title " +
                     "ORDER BY SUM(b.totalPrice) DESC")
       List<Object[]> getRevenueByMovieForCinema(@Param("cinemaId") Long cinemaId);

       @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM Booking b WHERE b.status = 'CONFIRMED'")
       BigDecimal sumTotalRevenue();

       // tổng số booking theo status
       long countByStatus(BookingStatus status);
}
