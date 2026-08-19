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

    /** Danh sách reviews của một phim, mới nhất trước. */
    List<MovieReview> findByMovieIdOrderByCreatedAtDesc(Long movieId);

    /** Kiểm tra customer đã review phim này chưa. */
    boolean existsByMovieIdAndCustomerId(Long movieId, Long customerId);

    /** Lấy review cụ thể của customer cho một phim (để xoá / kiểm tra quyền). */
    Optional<MovieReview> findByMovieIdAndCustomerId(Long movieId, Long customerId);

    /** Đếm tổng số reviews của một phim. */
    long countByMovieId(Long movieId);

    /** Tính điểm trung bình của một phim. Trả về null nếu chưa có review nào. */
    @Query("SELECT AVG(r.rating) FROM MovieReview r WHERE r.movie.id = :movieId")
    Double avgRatingByMovieId(@Param("movieId") Long movieId);

    /**
     * Đếm số lượng review theo từng mức sao (1–5) cho một phim.
     * Kết quả: Object[] với [rating, count].
     */
    @Query("SELECT r.rating, COUNT(r) FROM MovieReview r WHERE r.movie.id = :movieId GROUP BY r.rating ORDER BY r.rating DESC")
    List<Object[]> countByRatingForMovie(@Param("movieId") Long movieId);
}
