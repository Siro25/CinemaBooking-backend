package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Booking;
import com.sidocinemas.cinema_booking.domain.Movie;
import com.sidocinemas.cinema_booking.domain.MovieReview;
import com.sidocinemas.cinema_booking.domain.User;
import com.sidocinemas.cinema_booking.dto.request.ReviewRequest;
import com.sidocinemas.cinema_booking.dto.response.RatingSummaryResponse;
import com.sidocinemas.cinema_booking.dto.response.ReviewResponse;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.BookingRepository;
import com.sidocinemas.cinema_booking.repository.MovieRepository;
import com.sidocinemas.cinema_booking.repository.MovieReviewRepository;
import com.sidocinemas.cinema_booking.repository.UserRepository;
import com.sidocinemas.cinema_booking.service.ReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements ReviewService {

    MovieReviewRepository reviewRepository;
    MovieRepository movieRepository;
    UserRepository userRepository;
    BookingRepository bookingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }

        return reviewRepository.findByMovieIdOrderByCreatedAtDesc(movieId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RatingSummaryResponse getMovieRatingSummary(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }

        long totalReviews = reviewRepository.countByMovieId(movieId);
        Double avgRating = reviewRepository.avgRatingByMovieId(movieId);
        if (avgRating == null) {
            avgRating = 0.0;
        }

        long[] ratingCounts = new long[5];
        List<Object[]> counts = reviewRepository.countByRatingForMovie(movieId);
        for (Object[] row : counts) {
            int rating = (int) row[0];
            long count = (long) row[1];
            if (rating >= 1 && rating <= 5) {
                ratingCounts[rating - 1] = count;
            }
        }

        return RatingSummaryResponse.builder()
                .movieId(movieId)
                .averageRating(avgRating)
                .totalReviews(totalReviews)
                .ratingCounts(ratingCounts)
                .build();
    }

    @Override
    @Transactional
    public ReviewResponse createReview(Long movieId, Long customerId, ReviewRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // 1. Kiểm tra user đã review phim này chưa
        if (reviewRepository.existsByMovieIdAndCustomerId(movieId, customerId)) {
            throw new AppException(ErrorCode.REVIEW_ALREADY_EXISTS);
        }

        // 2. Kiểm tra user đã xem phim này chưa (có booking CONFIRMED cho phim này không)
        List<Booking> customerBookings = bookingRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
        boolean hasWatched = customerBookings.stream()
                .anyMatch(b -> b.getStatus() == BookingStatus.CONFIRMED 
                            && b.getShowtime() != null 
                            && b.getShowtime().getMovie() != null 
                            && b.getShowtime().getMovie().getId().equals(movieId));

        if (!hasWatched) {
            throw new AppException(ErrorCode.REVIEW_NOT_ALLOWED);
        }

        MovieReview review = MovieReview.builder()
                .movie(movie)
                .customer(customer)
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        review = reviewRepository.save(review);
        log.info("Customer {} created review for movie {}", customerId, movieId);

        return mapToResponse(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId, Long customerId) {
        MovieReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));

        if (!review.getCustomer().getId().equals(customerId)) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }

        reviewRepository.delete(review);
        log.info("Customer {} deleted review {}", customerId, reviewId);
    }

    private ReviewResponse mapToResponse(MovieReview review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .movieId(review.getMovie().getId())
                .customerId(review.getCustomer().getId())
                .customerName(review.getCustomer().getFullName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
