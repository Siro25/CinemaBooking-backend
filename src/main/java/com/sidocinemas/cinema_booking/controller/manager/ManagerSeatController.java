package com.sidocinemas.cinema_booking.controller.manager;

import com.sidocinemas.cinema_booking.dto.request.SeatRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.SeatResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.RoomRepository;
import com.sidocinemas.cinema_booking.repository.SeatRepository;
import com.sidocinemas.cinema_booking.service.ManagerContextService;
import com.sidocinemas.cinema_booking.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/manager/seats")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerSeatController {

    private final SeatService seatService;
    private final ManagerContextService managerContext;
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;

    @GetMapping("/room/{roomId}")
    public ApiResponse<List<SeatResponse>> getSeatsByRoom(@PathVariable Long roomId) {
        verifyRoomBelongsToManager(roomId);
        log.info("[MANAGER] Fetching seats for roomId={}", roomId);
        return ApiResponse.<List<SeatResponse>>builder()
                .data(seatService.getSeatsByRoom(roomId))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SeatResponse> createSeat(@Valid @RequestBody SeatRequest request) {
        verifyRoomBelongsToManager(request.getRoomId());
        log.info("[MANAGER] Creating seat row={} number={} in roomId={}",
                request.getRow(), request.getNumber(), request.getRoomId());
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.createSeat(request))
                .message("Tạo ghế thành công")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SeatResponse> updateSeat(@PathVariable Long id,
            @Valid @RequestBody SeatRequest request) {
        verifySeatBelongsToManager(id);
        verifyRoomBelongsToManager(request.getRoomId());
        log.info("[MANAGER] Updating seat id={}", id);
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.updateSeat(id, request))
                .message("Cập nhật ghế thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeat(@PathVariable Long id) {
        verifySeatBelongsToManager(id);
        log.info("[MANAGER] Deleting seat id={}", id);
        seatService.deleteSeat(id);
    }

    private void verifyRoomBelongsToManager(Long roomId) {
        Long managerCinemaId = managerContext.getCurrentManagerCinemaId();
        var room = roomRepository.findById(roomId)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        if (room.getCinema() == null || !Objects.equals(room.getCinema().getId(), managerCinemaId)) {
            throw new AppException(ErrorCode.UNAUTHORIZED); // Hoặc tạo ErrorCode.FORBIDDEN_ACTION
        }
    }

    private void verifySeatBelongsToManager(Long seatId) {
        var seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_FOUND));
        verifyRoomBelongsToManager(seat.getRoom().getId());
    }
}
