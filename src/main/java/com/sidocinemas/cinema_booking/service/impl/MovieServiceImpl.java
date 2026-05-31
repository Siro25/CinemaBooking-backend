package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Movie;
import com.sidocinemas.cinema_booking.dto.request.MovieRequest;
import com.sidocinemas.cinema_booking.dto.response.MovieResponse;
import com.sidocinemas.cinema_booking.enums.MovieStatus;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.MovieRepository;
import com.sidocinemas.cinema_booking.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    @Transactional
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .duration(request.getDuration())
                .genre(request.getGenre())
                .ageRating(request.getAgeRating())
                .posterUrl(request.getPosterUrl())
                .status(request.getStatus() != null ? request.getStatus() : MovieStatus.COMING_SOON)
                .build();

        movie = movieRepository.save(movie);
        return mapToResponse(movie);
    }

    @Override
    @Transactional
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDuration(request.getDuration());
        movie.setGenre(request.getGenre());
        movie.setAgeRating(request.getAgeRating());
        movie.setPosterUrl(request.getPosterUrl());
        
        if (request.getStatus() != null) {
            movie.setStatus(request.getStatus());
        }

        movie = movieRepository.save(movie);
        return mapToResponse(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));
        return mapToResponse(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }
        movieRepository.deleteById(id);
    }

    private MovieResponse mapToResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .genre(movie.getGenre())
                .ageRating(movie.getAgeRating())
                .posterUrl(movie.getPosterUrl())
                .status(movie.getStatus())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();
    }
}
