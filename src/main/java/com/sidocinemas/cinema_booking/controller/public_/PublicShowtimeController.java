package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.ShowtimeResponse;
import com.sidocinemas.cinema_booking.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Public Showtime Controller - Không cần authentication
 * Endpoint: /api/v1/public/showtimes
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/public/showtimes")
@RequiredArgsConstructor
public class PublicShowtimeController {

    private final ShowtimeService showtimeService;

    /**
     * Lấy lịch chiếu theo movie ID (cho trang đặt vé)
     */
    @GetMapping("/movie/{movieId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovie(@PathVariable Long movieId) {
        log.info("Public: Getting showtimes for movieId={}", movieId);
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .data(showtimeService.getShowtimesByMovie(movieId))
                .build();
    }

    /**
     * Xem chi tiết một showtime (số ghế trống, giá vé...)
     */
    @GetMapping("/{id}")
    public ApiResponse<ShowtimeResponse> getShowtimeById(@PathVariable Long id) {
        log.info("Public: Getting showtime by id={}", id);
        return ApiResponse.<ShowtimeResponse>builder()
                .data(showtimeService.getShowtimeById(id))
                .build();
    }
}