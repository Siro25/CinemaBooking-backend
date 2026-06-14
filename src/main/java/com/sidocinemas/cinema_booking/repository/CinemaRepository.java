package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findByManagerId(Long managerId);
    Optional<Cinema> findByName(String name);
}
