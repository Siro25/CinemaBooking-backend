package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.SeatRequest;
import com.sidocinemas.cinema_booking.dto.response.SeatResponse;

import java.util.List;

public interface SeatService {

    SeatResponse createSeat(SeatRequest request);

    SeatResponse updateSeat(Long id, SeatRequest request);

    void deleteSeat(Long id);

    SeatResponse getSeatById(Long id);

    List<SeatResponse> getSeatsByRoom(Long roomId);

    List<SeatResponse> getSeatsByShowtime(Long showtimeId);
}
