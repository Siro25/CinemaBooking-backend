package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Cinema;
import com.sidocinemas.cinema_booking.domain.Room;
import com.sidocinemas.cinema_booking.dto.request.RoomRequest;
import com.sidocinemas.cinema_booking.dto.response.RoomResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.CinemaRepository;
import com.sidocinemas.cinema_booking.repository.RoomRepository;
import com.sidocinemas.cinema_booking.service.RoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository;
    CinemaRepository cinemaRepository;

    @Override
    @Transactional
    public RoomResponse createRoom(RoomRequest request) {
        Cinema cinema = cinemaRepository.findById(request.getCinemaId())
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));

        Room room = Room.builder()
                .roomNumber(request.getRoomNumber())
                .type(request.getType())
                .capacity(request.getCapacity())
                .cinema(cinema)
                .build();

        room = roomRepository.save(room);
        return mapToResponse(room);
    }

    @Override
    @Transactional
    public RoomResponse updateRoom(Long id, RoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        Cinema cinema = cinemaRepository.findById(request.getCinemaId())
                .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));

        room.setRoomNumber(request.getRoomNumber());
        room.setType(request.getType());
        room.setCapacity(request.getCapacity());
        room.setCinema(cinema);

        room = roomRepository.save(room);
        return mapToResponse(room);
    }

    @Override
    @Transactional
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new AppException(ErrorCode.ROOM_NOT_FOUND);
        }
        roomRepository.deleteById(id);
    }

    @Override
    public RoomResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        return mapToResponse(room);
    }

    @Override
    public List<RoomResponse> getAllRoomsByCinema(Long cinemaId) {
        if (!cinemaRepository.existsById(cinemaId)) {
            throw new AppException(ErrorCode.CINEMA_NOT_FOUND);
        }
        return roomRepository.findByCinemaId(cinemaId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RoomResponse mapToResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .type(room.getType())
                .capacity(room.getCapacity())
                .cinemaId(room.getCinema() != null ? room.getCinema().getId() : null)
                .cinemaName(room.getCinema() != null ? room.getCinema().getName() : null)
                .build();
    }
}