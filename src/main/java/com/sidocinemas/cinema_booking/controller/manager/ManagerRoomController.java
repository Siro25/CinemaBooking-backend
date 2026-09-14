package com.sidocinemas.cinema_booking.controller.manager;

import com.sidocinemas.cinema_booking.dto.request.RoomRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.RoomResponse;
import com.sidocinemas.cinema_booking.service.ManagerContextService;
import com.sidocinemas.cinema_booking.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/manager/rooms")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerRoomController {

    private final RoomService roomService;
    private final ManagerContextService managerContext;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RoomResponse> createRoom(@Valid @RequestBody RoomRequest request) {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Creating room for cinemaId={}", cinemaId);
        return ApiResponse.<RoomResponse>builder()
                .data(roomService.createRoom(request))
                .message("Tạo phòng chiếu thành công")
                .build();
    }

    @GetMapping
    public ApiResponse<List<RoomResponse>> getAllRooms() {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Fetching rooms for cinemaId={}", cinemaId);
        return ApiResponse.<List<RoomResponse>>builder()
                .data(roomService.getAllRoomsByCinema(cinemaId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RoomResponse> getRoomById(@PathVariable Long id) {
        log.info("[MANAGER] Getting room id={}", id);
        return ApiResponse.<RoomResponse>builder()
                .data(roomService.getRoomById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<RoomResponse> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomRequest request) {
        log.info("[MANAGER] Updating room id={}", id);
        return ApiResponse.<RoomResponse>builder()
                .data(roomService.updateRoom(id, request))
                .message("Cập nhật phòng chiếu thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable Long id) {
        log.info("[MANAGER] Deleting room id={}", id);
        roomService.deleteRoom(id);
    }
}
