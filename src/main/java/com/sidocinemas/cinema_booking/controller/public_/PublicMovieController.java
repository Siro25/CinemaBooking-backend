package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.MovieResponse;
import com.sidocinemas.cinema_booking.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/public/movies")
@RequiredArgsConstructor
public class PublicMovieController {

    private final MovieService movieService;

    @GetMapping
    public ApiResponse<List<MovieResponse>> getAllMovies() {
        log.info("Public: Fetching all movies");
        return ApiResponse.<List<MovieResponse>>builder()
                .data(movieService.getAllMovies())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<MovieResponse> getMovieById(@PathVariable Long id) {
        log.info("Public: Getting movie by id={}", id);
        return ApiResponse.<MovieResponse>builder()
                .data(movieService.getMovieById(id))
                .build();
    }
}