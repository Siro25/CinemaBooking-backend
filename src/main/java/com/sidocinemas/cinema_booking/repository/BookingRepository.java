package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Booking;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Lấy lịch sử đặt vé của customer
    List<Booking> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    List<Booking> findByStatus(BookingStatus status);

    // Manager: lấy tất cả booking của một rạp (qua showtime → room → cinema)
    @Query("SELECT b FROM Booking b " +
           "JOIN b.showtime s JOIN s.room r JOIN r.cinema c " +
           "WHERE c.id = :cinemaId " +
           "ORDER BY b.createdAt DESC")
    List<Booking> findByCinemaIdOrderByCreatedAtDesc(@Param("cinemaId") Long cinemaId);

    // Manager report: tổng doanh thu của rạp (chỉ CONFIRMED)
    @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM Booking b " +
           "JOIN b.showtime s JOIN s.room r JOIN r.cinema c " +
           "WHERE c.id = :cinemaId AND b.status = 'CONFIRMED'")
    BigDecimal sumRevenueByCinema(@Param("cinemaId") Long cinemaId);

    // Manager report: doanh thu theo từng phim trong rạp
    @Query("SELECT s.movie.title, COALESCE(SUM(b.totalPrice), 0), COUNT(b) " +
           "FROM Booking b " +
           "JOIN b.showtime s JOIN s.room r JOIN r.cinema c " +
           "WHERE c.id = :cinemaId AND b.status = 'CONFIRMED' " +
           "GROUP BY s.movie.title " +
           "ORDER BY SUM(b.totalPrice) DESC")
    List<Object[]> getRevenueByMovieForCinema(@Param("cinemaId") Long cinemaId);

    // Admin report: tổng doanh thu toàn hệ thống (chỉ CONFIRMED)
    @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM Booking b WHERE b.status = 'CONFIRMED'")
    BigDecimal sumTotalRevenue();

    // Admin report: tổng số booking theo status
    long countByStatus(BookingStatus status);
}
