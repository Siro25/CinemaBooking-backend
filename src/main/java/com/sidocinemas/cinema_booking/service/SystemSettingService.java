package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.SystemSettingRequest;
import com.sidocinemas.cinema_booking.dto.response.SystemSettingResponse;

public interface SystemSettingService {
    SystemSettingResponse getSettings();
    SystemSettingResponse updateSettings(SystemSettingRequest request);
}
