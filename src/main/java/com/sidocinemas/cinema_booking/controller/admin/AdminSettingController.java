package com.sidocinemas.cinema_booking.controller.admin;

import com.sidocinemas.cinema_booking.dto.request.SystemSettingRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.SystemSettingResponse;
import com.sidocinemas.cinema_booking.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/settings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminSettingController {

    private final SystemSettingService settingService;

    @GetMapping
    public ApiResponse<SystemSettingResponse> getSettings() {
        log.info("[ADMIN] Fetching system settings");
        return ApiResponse.<SystemSettingResponse>builder()
                .data(settingService.getSettings())
                .message("Lấy cấu hình hệ thống thành công")
                .build();
    }

    @PutMapping
    public ApiResponse<SystemSettingResponse> updateSettings(@RequestBody SystemSettingRequest request) {
        log.info("[ADMIN] Updating system settings");
        return ApiResponse.<SystemSettingResponse>builder()
                .data(settingService.updateSettings(request))
                .message("Cập nhật cấu hình hệ thống thành công")
                .build();
    }
}
