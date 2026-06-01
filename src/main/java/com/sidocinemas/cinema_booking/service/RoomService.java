package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.RoomRequest;
import com.sidocinemas.cinema_booking.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse createRoom(RoomRequest request);
    RoomResponse updateRoom(Long id, RoomRequest request);
    void deleteRoom(Long id);
    RoomResponse getRoomById(Long id);
    List<RoomResponse> getAllRoomsByCinema(Long cinemaId);
}