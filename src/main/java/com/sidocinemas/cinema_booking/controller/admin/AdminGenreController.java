package com.sidocinemas.cinema_booking.controller.admin;

import com.sidocinemas.cinema_booking.dto.request.GenreRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.GenreResponse;
import com.sidocinemas.cinema_booking.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/genres")
@RequiredArgsConstructor
public class AdminGenreController {

    private final GenreService genreService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<GenreResponse>> getAllGenres() {
        return ApiResponse.<List<GenreResponse>>builder()
                .data(genreService.getAllGenres())
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<GenreResponse> getGenreById(@PathVariable Long id) {
        return ApiResponse.<GenreResponse>builder()
                .data(genreService.getGenreById(id))
                .build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<GenreResponse> createGenre(@Valid @RequestBody GenreRequest request) {
        log.info("[ADMIN] Creating genre: {}", request.getName());
        return ApiResponse.<GenreResponse>builder()
                .data(genreService.createGenre(request))
                .message("Thêm thể loại thành công")
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<GenreResponse> updateGenre(@PathVariable Long id, @Valid @RequestBody GenreRequest request) {
        log.info("[ADMIN] Updating genre id={}", id);
        return ApiResponse.<GenreResponse>builder()
                .data(genreService.updateGenre(id, request))
                .message("Cập nhật thể loại thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id) {
        log.info("[ADMIN] Deleting genre id={}", id);
        genreService.deleteGenre(id);
    }
}
