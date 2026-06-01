package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.CinemaRequest;
import com.sidocinemas.cinema_booking.dto.response.CinemaResponse;

import java.util.List;

public interface CinemaService {
    CinemaResponse createCinema(CinemaRequest request);

    CinemaResponse updateCinema(Long id, CinemaRequest request);

    void deleteCinema(Long id);

    CinemaResponse getCinemaById(Long id);

    List<CinemaResponse> getAllCinemas();
}