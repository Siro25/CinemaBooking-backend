package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Seat;
import com.sidocinemas.cinema_booking.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // tính toán trạng thái "Seat Availability"
    @Query("SELECT t.seat FROM Ticket t WHERE t.booking.showtime.id = :showtimeId AND t.booking.status != 'CANCELLED'")
    List<Seat> findBookedSeatsByShowtimeId(@Param("showtimeId") Long showtimeId);

    // Kiểm tra xem danh sách các ghế khách hàng đang cố đặt có ghế nào vừa được đặt
    // từ user khác không
    @Query("SELECT COUNT(t) > 0 FROM Ticket t WHERE t.booking.showtime.id = :showtimeId " +
            "AND t.seat.id IN :seatIds AND t.booking.status != 'CANCELLED'")
    boolean existsBookedSeats(@Param("showtimeId") Long showtimeId, @Param("seatIds") List<Long> seatIds);
}
