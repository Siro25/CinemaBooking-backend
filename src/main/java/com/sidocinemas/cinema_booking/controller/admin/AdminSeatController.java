package com.sidocinemas.cinema_booking.controller.admin;

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

/**
 * Admin Seat Controller - Quản lý ghế
 * Endpoint: /api/v1/admin/seats
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/admin/seats")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminSeatController {

    private final SeatService seatService;

    /**
     * Tạo ghế mới
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SeatResponse> createSeat(@Valid @RequestBody SeatRequest request) {
        log.info("[ADMIN] Creating seat row={} number={} in roomId={}", 
                request.getRow(), request.getNumber(), request.getRoomId());
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.createSeat(request))
                .message("Tạo ghế thành công")
                .build();
    }

    /**
     * Cập nhật ghế
     */
    @PutMapping("/{id}")
    public ApiResponse<SeatResponse> updateSeat(@PathVariable Long id,
            @Valid @RequestBody SeatRequest request) {
        log.info("[ADMIN] Updating seat id={}", id);
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.updateSeat(id, request))
                .message("Cập nhật ghế thành công")
                .build();
    }

    /**
     * Xóa ghế
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeat(@PathVariable Long id) {
        log.info("[ADMIN] Deleting seat id={}", id);
        seatService.deleteSeat(id);
    }
}