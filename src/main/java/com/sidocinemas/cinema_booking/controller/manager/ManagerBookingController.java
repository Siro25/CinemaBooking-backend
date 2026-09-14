package com.sidocinemas.cinema_booking.controller.manager;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.service.BookingService;
import com.sidocinemas.cinema_booking.service.ManagerContextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/manager/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerBookingController {

    private final BookingService bookingService;
    private final ManagerContextService managerContext;

    @GetMapping
    public ApiResponse<List<BookingResponse>> getAllBookings() {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Fetching all bookings for cinemaId={}", cinemaId);
        return ApiResponse.<List<BookingResponse>>builder()
                .data(bookingService.getBookingsByCinema(cinemaId))
                .build();
    }

    @PatchMapping("/{id}/confirm")
    public ApiResponse<BookingResponse> confirmBooking(@PathVariable Long id) {
        log.info("[MANAGER] Confirming booking id={}", id);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.confirmBooking(id))
                .message("Xác nhận đặt vé thành công")
                .build();
    }

    @PatchMapping("/{id}/cancel")
    public ApiResponse<BookingResponse> cancelBooking(@PathVariable Long id) {
        log.info("[MANAGER] Cancelling booking id={}", id);
        return ApiResponse.<BookingResponse>builder()
                .data(bookingService.cancelBooking(id))
                .message("Huỷ đặt vé thành công")
                .build();
    }
}
