package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Seat;
import com.sidocinemas.cinema_booking.domain.Ticket;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

        /**
         * Lấy danh sách ghế đang bị chiếm hợp lệ dùng để hiển thị sơ đồ ghế.
         * Dùng enum parameter thay string literal để tránh lỗi typo lúc runtime.
         */
        @Query("SELECT t.seat FROM Ticket t " +
                        "WHERE t.booking.showtime.id = :showtimeId " +
                        "AND (t.booking.status = :confirmed " +
                        "     OR (t.booking.status = :hold AND t.booking.createdAt > :expirationTime))")
        List<Seat> findBookedSeatsByShowtimeIdInternal(
                        @Param("showtimeId") Long showtimeId,
                        @Param("expirationTime") LocalDateTime expirationTime,
                        @Param("confirmed") BookingStatus confirmed,
                        @Param("hold") BookingStatus hold);

        /** callers không cần biết enum constants. */
        default List<Seat> findBookedSeatsByShowtimeId(Long showtimeId, LocalDateTime expirationTime) {
                return findBookedSeatsByShowtimeIdInternal(
                                showtimeId, expirationTime, BookingStatus.CONFIRMED, BookingStatus.HOLD);
        }

        /**
         * Kiểm tra nhanh xem có ghế nào trong danh sách đang bị giữ hợp lệ không.
         * CASE WHEN là workaround vì JPQL không hỗ trợ EXISTS subquery trực tiếp.
         */
        @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
                        "FROM Ticket t " +
                        "WHERE t.booking.showtime.id = :showtimeId " +
                        "AND t.seat.id IN :seatIds " +
                        "AND (t.booking.status = :confirmed " +
                        "     OR (t.booking.status = :hold AND t.booking.createdAt > :expirationTime))")
        boolean existsBookedSeatsInternal(
                        @Param("showtimeId") Long showtimeId,
                        @Param("seatIds") List<Long> seatIds,
                        @Param("expirationTime") LocalDateTime expirationTime,
                        @Param("confirmed") BookingStatus confirmed,
                        @Param("hold") BookingStatus hold);

        /** Convenience method — callers không cần biết enum constants. */
        default boolean existsBookedSeats(Long showtimeId, List<Long> seatIds, LocalDateTime expirationTime) {
                return existsBookedSeatsInternal(
                                showtimeId, seatIds, expirationTime, BookingStatus.CONFIRMED, BookingStatus.HOLD);
        }
}
