package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.CinemaResponse;
import com.sidocinemas.cinema_booking.service.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/public/cinemas")
@RequiredArgsConstructor
public class PublicCinemaController {

    private final CinemaService cinemaService;

    @GetMapping
    public ApiResponse<List<CinemaResponse>> getAllCinemas() {
        log.info("Public: Fetching all cinemas");
        return ApiResponse.<List<CinemaResponse>>builder()
                .data(cinemaService.getAllCinemas())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CinemaResponse> getCinemaById(@PathVariable Long id) {
        log.info("Public: Getting cinema by id={}", id);
        return ApiResponse.<CinemaResponse>builder()
                .data(cinemaService.getCinemaById(id))
                .build();
    }
}