package com.sidocinemas.cinema_booking.controller.manager;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.ManagerReportResponse;
import com.sidocinemas.cinema_booking.service.BookingService;
import com.sidocinemas.cinema_booking.service.ManagerContextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MANAGER: Xem báo cáo doanh thu rạp của mình.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/manager/reports")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerReportController {

    private final BookingService bookingService;
    private final ManagerContextService managerContext;

    @GetMapping
    public ApiResponse<ManagerReportResponse> getReport() {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Generating report for cinemaId={}", cinemaId);
        return ApiResponse.<ManagerReportResponse>builder()
                .data(bookingService.getManagerReport(cinemaId))
                .message("Báo cáo doanh thu rạp")
                .build();
    }
}
