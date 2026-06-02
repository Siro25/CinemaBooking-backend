package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.ShowtimeRequest;
import com.sidocinemas.cinema_booking.dto.response.ShowtimeResponse;

import java.util.List;

public interface ShowtimeService {

    ShowtimeResponse createShowtime(ShowtimeRequest request);

    ShowtimeResponse updateShowtime(Long id, ShowtimeRequest request);

    void deleteShowtime(Long id);

    ShowtimeResponse getShowtimeById(Long id);

    List<ShowtimeResponse> getShowtimesByMovie(Long movieId);

    List<ShowtimeResponse> getShowtimesByRoom(Long roomId);

    List<ShowtimeResponse> getAllShowtimes();
}
