package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    // Customer: Xem showtime theo phim
    List<Showtime> findByMovieId(Long movieId);

    // Manger / System: Xem showtime theo phòng chiếu
    List<Showtime> findByRoomId(Long roomId);

    // Kiểm tra Overlap (không được trùng lịch)
    @Query("SELECT s FROM Showtime s WHERE s.room.id = :roomId AND " +
            "(s.startTime < :endTime AND s.endTime > :startTime)")
    List<Showtime> findOverlappingShowtimes(@Param("roomId") Long roomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
}
