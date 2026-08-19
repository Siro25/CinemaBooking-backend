package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.ReviewRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.ReviewResponse;
import com.sidocinemas.cinema_booking.service.ReviewService;
import com.sidocinemas.cinema_booking.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerReviewController {

    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResponse> createReview(
            @PathVariable Long movieId,
            @Valid @RequestBody ReviewRequest request) {
        Long customerId = SecurityUtils.getCurrentUserId();
        return ApiResponse.<ReviewResponse>builder()
                .data(reviewService.createReview(movieId, customerId, request))
                .message("Đánh giá của bạn đã được ghi nhận")
                .build();
    }

    @DeleteMapping("/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long reviewId) {
        Long customerId = SecurityUtils.getCurrentUserId();
        reviewService.deleteReview(reviewId, customerId);
    }
}
