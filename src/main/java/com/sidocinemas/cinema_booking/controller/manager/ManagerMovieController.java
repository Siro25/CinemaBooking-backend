package com.sidocinemas.cinema_booking.controller.manager;

import com.sidocinemas.cinema_booking.dto.request.MovieRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.MovieResponse;
import com.sidocinemas.cinema_booking.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/manager/movies")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerMovieController {

    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        log.info("[MANAGER] Creating movie: {}", request.getTitle());
        return ApiResponse.<MovieResponse>builder()
                .data(movieService.createMovie(request))
                .message("Tạo phim thành công")
                .build();
    }

    @GetMapping
    public ApiResponse<List<MovieResponse>> getAllMovies() {
        log.info("[MANAGER] Fetching all movies");
        return ApiResponse.<List<MovieResponse>>builder()
                .data(movieService.getAllMovies())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<MovieResponse> getMovieById(@PathVariable Long id) {
        log.info("[MANAGER] Getting movie id={}", id);
        return ApiResponse.<MovieResponse>builder()
                .data(movieService.getMovieById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<MovieResponse> updateMovie(
            @PathVariable Long id,
            @Valid @RequestBody MovieRequest request) {
        log.info("[MANAGER] Updating movie id={}", id);
        return ApiResponse.<MovieResponse>builder()
                .data(movieService.updateMovie(id, request))
                .message("Cập nhật phim thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        log.info("[MANAGER] Deleting movie id={}", id);
        movieService.deleteMovie(id);
    }
}
