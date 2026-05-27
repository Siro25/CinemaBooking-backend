package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.Movie;
import com.sidocinemas.cinema_booking.enums.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByStatus(MovieStatus status);
}
