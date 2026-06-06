package com.sidocinemas.cinema_booking.controller.admin;

import com.sidocinemas.cinema_booking.dto.response.AdminReportResponse;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.service.AdminReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ADMIN: Xem tổng quan dashboard.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    private final AdminReportService adminReportService;

    @GetMapping
    public ApiResponse<AdminReportResponse> getDashboard() {
        log.info("[ADMIN] Generating system dashboard");
        return ApiResponse.<AdminReportResponse>builder()
                .data(adminReportService.getSystemReport())
                .message("Dashboard hệ thống")
                .build();
    }
}
