package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.ReviewRequest;
import com.sidocinemas.cinema_booking.dto.response.RatingSummaryResponse;
import com.sidocinemas.cinema_booking.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    List<ReviewResponse> getReviewsByMovie(Long movieId);

    RatingSummaryResponse getMovieRatingSummary(Long movieId);

    ReviewResponse createReview(Long movieId, Long customerId, ReviewRequest request);

    void deleteReview(Long reviewId, Long customerId);
}
