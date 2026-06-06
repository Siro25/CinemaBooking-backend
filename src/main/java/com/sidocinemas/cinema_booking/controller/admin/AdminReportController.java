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
 * ADMIN: Xem báo cáo tổng hợp toàn hệ thống.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/admin/reports")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminReportController {

    private final AdminReportService adminReportService;

    @GetMapping
    public ApiResponse<AdminReportResponse> getReport() {
        log.info("[ADMIN] Generating system report");
        return ApiResponse.<AdminReportResponse>builder()
                .data(adminReportService.getSystemReport())
                .message("Báo cáo hệ thống")
                .build();
    }
}
