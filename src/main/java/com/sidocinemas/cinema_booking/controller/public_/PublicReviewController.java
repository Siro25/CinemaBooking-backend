package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.RatingSummaryResponse;
import com.sidocinemas.cinema_booking.dto.response.ReviewResponse;
import com.sidocinemas.cinema_booking.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/movies/{movieId}")
@RequiredArgsConstructor
public class PublicReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ApiResponse<List<ReviewResponse>> getReviews(@PathVariable Long movieId) {
        return ApiResponse.<List<ReviewResponse>>builder()
                .data(reviewService.getReviewsByMovie(movieId))
                .build();
    }

    @GetMapping("/rating")
    public ApiResponse<RatingSummaryResponse> getRatingSummary(@PathVariable Long movieId) {
        return ApiResponse.<RatingSummaryResponse>builder()
                .data(reviewService.getMovieRatingSummary(movieId))
                .build();
    }
}
