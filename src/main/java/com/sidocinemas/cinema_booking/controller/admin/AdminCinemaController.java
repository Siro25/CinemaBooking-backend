package com.sidocinemas.cinema_booking.controller.admin;

import com.sidocinemas.cinema_booking.dto.request.CinemaRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.CinemaResponse;
import com.sidocinemas.cinema_booking.service.CinemaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ADMIN: Quản lý rạp phim.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/admin/cinemas")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminCinemaController {

    private final CinemaService cinemaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CinemaResponse> createCinema(@Valid @RequestBody CinemaRequest request) {
        log.info("[ADMIN] Creating cinema");
        return ApiResponse.<CinemaResponse>builder()
                .data(cinemaService.createCinema(request))
                .message("Tạo rạp thành công")
                .build();
    }

    @GetMapping
    public ApiResponse<List<CinemaResponse>> getAllCinemas() {
        log.info("[ADMIN] Fetching all cinemas");
        return ApiResponse.<List<CinemaResponse>>builder()
                .data(cinemaService.getAllCinemas())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CinemaResponse> getCinemaById(@PathVariable Long id) {
        log.info("[ADMIN] Getting cinema id={}", id);
        return ApiResponse.<CinemaResponse>builder()
                .data(cinemaService.getCinemaById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CinemaResponse> updateCinema(
            @PathVariable Long id,
            @Valid @RequestBody CinemaRequest request) {
        log.info("[ADMIN] Updating cinema id={}", id);
        return ApiResponse.<CinemaResponse>builder()
                .data(cinemaService.updateCinema(id, request))
                .message("Cập nhật rạp thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCinema(@PathVariable Long id) {
        log.info("[ADMIN] Deleting cinema id={}", id);
        cinemaService.deleteCinema(id);
    }
}
