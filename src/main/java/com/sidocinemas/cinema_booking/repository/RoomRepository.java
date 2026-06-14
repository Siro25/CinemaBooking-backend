package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCinemaId(Long cinemaId);
    Optional<Room> findByRoomNumber(String roomNumber);
}
