package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Genre;
import com.sidocinemas.cinema_booking.dto.request.GenreRequest;
import com.sidocinemas.cinema_booking.dto.response.GenreResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.GenreRepository;
import com.sidocinemas.cinema_booking.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Cacheable(value = "genres", key = "'all'")
    public List<GenreResponse> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "genres", key = "#id")
    public GenreResponse getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_FOUND));
        return mapToResponse(genre);
    }

    @Override
    @Transactional
    @CacheEvict(value = "genres", allEntries = true)
    public GenreResponse createGenre(GenreRequest request) {
        if (genreRepository.existsByNameIgnoreCase(request.getName())) {
            throw new AppException(ErrorCode.GENRE_ALREADY_EXISTS);
        }
        Genre genre = Genre.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return mapToResponse(genreRepository.save(genre));
    }

    @Override
    @Transactional
    @CacheEvict(value = "genres", allEntries = true)
    public GenreResponse updateGenre(Long id, GenreRequest request) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_FOUND));

        // Kiểm tra tên trùng (ngoại trừ chính nó)
        genreRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new AppException(ErrorCode.GENRE_ALREADY_EXISTS);
                    }
                });

        genre.setName(request.getName());
        genre.setDescription(request.getDescription());
        return mapToResponse(genreRepository.save(genre));
    }

    @Override
    @Transactional
    @CacheEvict(value = "genres", allEntries = true)
    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.GENRE_NOT_FOUND));
        genreRepository.delete(genre);
    }

    private GenreResponse mapToResponse(Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .createdAt(genre.getCreatedAt())
                .build();
    }
}
