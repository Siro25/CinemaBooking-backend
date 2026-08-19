package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.ReviewRequest;
import com.sidocinemas.cinema_booking.dto.response.RatingSummaryResponse;
import com.sidocinemas.cinema_booking.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    /** 
     * Lấy danh sách review của 1 phim.
     */
    List<ReviewResponse> getReviewsByMovie(Long movieId);

    /** 
     * Lấy tóm tắt điểm đánh giá (avg rating, count by star) của 1 phim.
     */
    RatingSummaryResponse getMovieRatingSummary(Long movieId);

    /** 
     * Thêm mới 1 đánh giá.
     * Yêu cầu: customerId phải có booking CONFIRMED cho movieId.
     */
    ReviewResponse createReview(Long movieId, Long customerId, ReviewRequest request);

    /** 
     * Xoá đánh giá.
     * Yêu cầu: customerId phải là người tạo đánh giá.
     */
    void deleteReview(Long reviewId, Long customerId);
}
