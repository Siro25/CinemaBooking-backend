package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.GenreRequest;
import com.sidocinemas.cinema_booking.dto.response.GenreResponse;

import java.util.List;

public interface GenreService {
    List<GenreResponse> getAllGenres();
    GenreResponse getGenreById(Long id);
    GenreResponse createGenre(GenreRequest request);
    GenreResponse updateGenre(Long id, GenreRequest request);
    void deleteGenre(Long id);
}
