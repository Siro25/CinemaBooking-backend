package com.sidocinemas.cinema_booking.controller;

import com.sidocinemas.cinema_booking.dto.request.MovieRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.MovieResponse;
import com.sidocinemas.cinema_booking.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ApiResponse<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .data(movieService.createMovie(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<MovieResponse>> getAllMovies() {
        return ApiResponse.<List<MovieResponse>>builder()
                .data(movieService.getAllMovies())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<MovieResponse> getMovieById(@PathVariable Long id) {
        return ApiResponse.<MovieResponse>builder()
                .data(movieService.getMovieById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<MovieResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequest request) {
        return ApiResponse.<MovieResponse>builder()
                .data(movieService.updateMovie(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ApiResponse.<Void>builder()
                .message("Xóa phim thành công")
                .build();
    }
}
