package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.MovieRequest;
import com.sidocinemas.cinema_booking.dto.response.MovieResponse;

import java.util.List;

public interface MovieService {
    MovieResponse createMovie(MovieRequest request);
    MovieResponse updateMovie(Long id, MovieRequest request);
    MovieResponse getMovieById(Long id);
    List<MovieResponse> getAllMovies();
    void deleteMovie(Long id);
}
