package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.SystemSettingResponse;
import com.sidocinemas.cinema_booking.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/public/settings")
@RequiredArgsConstructor
public class PublicSettingController {

    private final SystemSettingService settingService;

    @GetMapping
    public ApiResponse<SystemSettingResponse> getSettings() {
        return ApiResponse.<SystemSettingResponse>builder()
                .data(settingService.getSettings())
                .message("Lấy cấu hình hệ thống thành công")
                .build();
    }
}
