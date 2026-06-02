package com.sidocinemas.cinema_booking.controller;

import com.sidocinemas.cinema_booking.dto.request.ShowtimeRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.ShowtimeResponse;
import com.sidocinemas.cinema_booking.service.ShowtimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    // ── ADMIN only ────────────────────────────────────────────────────────────

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ShowtimeResponse> createShowtime(@Valid @RequestBody ShowtimeRequest request) {
        log.info("Creating showtime for movieId={}, roomId={}", request.getMovieId(), request.getRoomId());
        return ApiResponse.<ShowtimeResponse>builder()
                .data(showtimeService.createShowtime(request))
                .message("Tạo suất chiếu thành công")
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<ShowtimeResponse> updateShowtime(@PathVariable Long id,
            @Valid @RequestBody ShowtimeRequest request) {
        log.info("Updating showtime id={}", id);
        return ApiResponse.<ShowtimeResponse>builder()
                .data(showtimeService.updateShowtime(id, request))
                .message("Cập nhật suất chiếu thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShowtime(@PathVariable Long id) {
        log.info("Deleting showtime id={}", id);
        showtimeService.deleteShowtime(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<ShowtimeResponse>> getAllShowtimes() {
        log.info("Fetching all showtimes");
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .data(showtimeService.getAllShowtimes())
                .build();
    }

    // ── CUSTOMER + ADMIN ──────────────────────────────────────────────────────

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<ShowtimeResponse> getShowtimeById(@PathVariable Long id) {
        log.info("Getting showtime id={}", id);
        return ApiResponse.<ShowtimeResponse>builder()
                .data(showtimeService.getShowtimeById(id))
                .build();
    }

    @GetMapping("/movie/{movieId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovie(@PathVariable Long movieId) {
        log.info("Getting showtimes for movieId={}", movieId);
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .data(showtimeService.getShowtimesByMovie(movieId))
                .build();
    }

    @GetMapping("/room/{roomId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByRoom(@PathVariable Long roomId) {
        log.info("Getting showtimes for roomId={}", roomId);
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .data(showtimeService.getShowtimesByRoom(roomId))
                .build();
    }
}
