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
    public CinemaResponse createCinema(CinemaRequest request) {
        User manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Cinema cinema = Cinema.builder()
                .name(request.getName())
                .address(request.getAddress())
                .manager(manager)
                .build();

        cinema = cinemaRepository.save(cinema);
        return mapToResponse(cinema);
    }

    @Override
    @Transactional
    public CinemaResponse updateCinema(Long id, CinemaRequest request) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));

        User manager = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        cinema.setName(request.getName());
        cinema.setAddress(request.getAddress());
        cinema.setManager(manager);

        cinema = cinemaRepository.save(cinema);
        return mapToResponse(cinema);
    }

    @Override
    @Transactional
    public void deleteCinema(Long id) {
        if (!cinemaRepository.existsById(id)) {
            throw new AppException(ErrorCode.CINEMA_NOT_FOUND);
        }
        cinemaRepository.deleteById(id);
    }

    @Override
    public CinemaResponse getCinemaById(Long id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));
        return mapToResponse(cinema);
    }

    @Override
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
                .managerId(cinema.getManager() != null ? cinema.getManager().getId() : null)
                .managerName(cinema.getManager() != null ? cinema.getManager().getFullName() : null)
                .build();
    }
}