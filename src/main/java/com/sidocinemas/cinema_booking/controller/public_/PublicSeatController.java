package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.SeatResponse;
import com.sidocinemas.cinema_booking.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/public/seats")
@RequiredArgsConstructor
public class PublicSeatController {

    private final SeatService seatService;

    @GetMapping("/room/{roomId}")
    public ApiResponse<List<SeatResponse>> getSeatsByRoom(@PathVariable Long roomId) {
        log.info("Public: Getting seats for roomId={}", roomId);
        return ApiResponse.<List<SeatResponse>>builder()
                .data(seatService.getSeatsByRoom(roomId))
                .build();
    }

    @GetMapping("/showtime/{showtimeId}")
    public ApiResponse<List<SeatResponse>> getSeatsByShowtime(@PathVariable Long showtimeId) {
        log.info("Public: Getting seat availability for showtimeId={}", showtimeId);
        return ApiResponse.<List<SeatResponse>>builder()
                .data(seatService.getSeatsByShowtime(showtimeId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<SeatResponse> getSeatById(@PathVariable Long id) {
        log.info("Public: Getting seat id={}", id);
        return ApiResponse.<SeatResponse>builder()
                .data(seatService.getSeatById(id))
                .build();
    }
}