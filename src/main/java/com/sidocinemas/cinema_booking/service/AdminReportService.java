package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.response.AdminReportResponse;

public interface AdminReportService {
    /** ADMIN: Báo cáo tổng hợp toàn hệ thống */
    AdminReportResponse getSystemReport();
}
