package com.sidocinemas.cinema_booking.controller.manager;

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

/**
 * MANAGER: Quản lý suất chiếu tại rạp mình.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/manager/showtimes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerShowtimeController {

    private final ShowtimeService showtimeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ShowtimeResponse> createShowtime(@Valid @RequestBody ShowtimeRequest request) {
        log.info("[MANAGER] Creating showtime for movieId={}, roomId={}", request.getMovieId(), request.getRoomId());
        return ApiResponse.<ShowtimeResponse>builder()
                .data(showtimeService.createShowtime(request))
                .message("Tạo suất chiếu thành công")
                .build();
    }

    @GetMapping
    public ApiResponse<List<ShowtimeResponse>> getAllShowtimes() {
        log.info("[MANAGER] Fetching all showtimes");
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .data(showtimeService.getAllShowtimes())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ShowtimeResponse> getShowtimeById(@PathVariable Long id) {
        log.info("[MANAGER] Getting showtime id={}", id);
        return ApiResponse.<ShowtimeResponse>builder()
                .data(showtimeService.getShowtimeById(id))
                .build();
    }

    @GetMapping("/movie/{movieId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByMovie(@PathVariable Long movieId) {
        log.info("[MANAGER] Getting showtimes for movieId={}", movieId);
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .data(showtimeService.getShowtimesByMovie(movieId))
                .build();
    }

    @GetMapping("/room/{roomId}")
    public ApiResponse<List<ShowtimeResponse>> getShowtimesByRoom(@PathVariable Long roomId) {
        log.info("[MANAGER] Getting showtimes for roomId={}", roomId);
        return ApiResponse.<List<ShowtimeResponse>>builder()
                .data(showtimeService.getShowtimesByRoom(roomId))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ShowtimeResponse> updateShowtime(
            @PathVariable Long id,
            @Valid @RequestBody ShowtimeRequest request) {
        log.info("[MANAGER] Updating showtime id={}", id);
        return ApiResponse.<ShowtimeResponse>builder()
                .data(showtimeService.updateShowtime(id, request))
                .message("Cập nhật suất chiếu thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShowtime(@PathVariable Long id) {
        log.info("[MANAGER] Deleting showtime id={}", id);
        showtimeService.deleteShowtime(id);
    }
}
