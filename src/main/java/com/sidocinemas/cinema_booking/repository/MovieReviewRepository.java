package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

    List<MovieReview> findByMovieIdOrderByCreatedAtDesc(Long movieId);

    boolean existsByMovieIdAndCustomerId(Long movieId, Long customerId);

    Optional<MovieReview> findByMovieIdAndCustomerId(Long movieId, Long customerId);

    long countByMovieId(Long movieId);

    @Query("SELECT AVG(r.rating) FROM MovieReview r WHERE r.movie.id = :movieId")
    Double avgRatingByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT r.rating, COUNT(r) FROM MovieReview r WHERE r.movie.id = :movieId GROUP BY r.rating ORDER BY r.rating DESC")
    List<Object[]> countByRatingForMovie(@Param("movieId") Long movieId);
}
