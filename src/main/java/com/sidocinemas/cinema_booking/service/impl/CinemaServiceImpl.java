package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Cinema;
import com.sidocinemas.cinema_booking.domain.User;
import com.sidocinemas.cinema_booking.dto.request.CinemaRequest;
import com.sidocinemas.cinema_booking.dto.response.CinemaResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.CinemaRepository;
import com.sidocinemas.cinema_booking.repository.UserRepository;
import com.sidocinemas.cinema_booking.service.CinemaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CinemaServiceImpl implements CinemaService {

    CinemaRepository cinemaRepository;
    UserRepository userRepository;

    @Override
    @Transactional
    @CacheEvict(value = "cinemas", allEntries = true)
    public CinemaResponse createCinema(CinemaRequest request) {
        Cinema cinema = Cinema.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();

        cinema = cinemaRepository.save(cinema);
        return mapToResponse(cinema);
    }

    @Override
    @Transactional
    @CacheEvict(value = "cinemas", allEntries = true)
    public CinemaResponse updateCinema(Long id, CinemaRequest request) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));

        cinema.setName(request.getName());
        cinema.setAddress(request.getAddress());

        cinema = cinemaRepository.save(cinema);
        return mapToResponse(cinema);
    }

    @Override
    @Transactional
    @CacheEvict(value = "cinemas", allEntries = true)
    public void deleteCinema(Long id) {
        if (!cinemaRepository.existsById(id)) {
            throw new AppException(ErrorCode.CINEMA_NOT_FOUND);
        }
        cinemaRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "cinemas", key = "#id")
    public CinemaResponse getCinemaById(Long id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));
        return mapToResponse(cinema);
    }

    @Override
    @Cacheable(value = "cinemas", key = "'all'")
    public List<CinemaResponse> getAllCinemas() {
        return cinemaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CinemaResponse mapToResponse(Cinema cinema) {
        return CinemaResponse.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .address(cinema.getAddress())
                .build();
    }
}