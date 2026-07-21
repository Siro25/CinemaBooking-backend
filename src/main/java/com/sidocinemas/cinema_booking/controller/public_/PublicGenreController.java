package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.GenreResponse;
import com.sidocinemas.cinema_booking.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Public Genre Controller - Không cần xác thực
 * Endpoint: /api/v1/public/genres
 */
@RestController
@RequestMapping("/api/v1/public/genres")
@RequiredArgsConstructor
public class PublicGenreController {

    private final GenreService genreService;

    @GetMapping
    public ApiResponse<List<GenreResponse>> getAllGenres() {
        return ApiResponse.<List<GenreResponse>>builder()
                .data(genreService.getAllGenres())
                .build();
    }
}
