package com.sidocinemas.cinema_booking.controller;

import com.sidocinemas.cinema_booking.dto.request.SeatRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.SeatResponse;
import com.sidocinemas.cinema_booking.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    // ── ADMIN only ────────────────────────────────────────────────────────────

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SeatResponse> createSeat(@Valid @RequestBody SeatRequest request) {
        log.info("Creating seat row={} number={} in roomId={}", request.getRow(), request.getNumber(), request.getRoomId());
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.createSeat(request))
                .message("Tạo ghế thành công")
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<SeatResponse> updateSeat(@PathVariable Long id,
            @Valid @RequestBody SeatRequest request) {
        log.info("Updating seat id={}", id);
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.updateSeat(id, request))
                .message("Cập nhật ghế thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeat(@PathVariable Long id) {
        log.info("Deleting seat id={}", id);
        seatService.deleteSeat(id);
    }

    // ── CUSTOMER + ADMIN ──────────────────────────────────────────────────────

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<SeatResponse> getSeatById(@PathVariable Long id) {
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.getSeatById(id))
                .build();
    }

    @GetMapping("/room/{roomId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<List<SeatResponse>> getSeatsByRoom(@PathVariable Long roomId) {
        log.info("Getting seats for roomId={}", roomId);
        return ApiResponse.<List<SeatResponse>>builder()
                .data(seatService.getSeatsByRoom(roomId))
                .build();
    }

    /**
     * Lấy danh sách ghế kèm trạng thái available/booked theo suất chiếu.
     * Customer dùng endpoint này để chọn ghế khi đặt vé.
     */
    @GetMapping("/showtime/{showtimeId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ApiResponse<List<SeatResponse>> getSeatsByShowtime(@PathVariable Long showtimeId) {
        log.info("Getting seat availability for showtimeId={}", showtimeId);
        return ApiResponse.<List<SeatResponse>>builder()
                .data(seatService.getSeatsByShowtime(showtimeId))
                .build();
    }
}
