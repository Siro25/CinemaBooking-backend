package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * Báo cáo tổng hợp toàn hệ thống – dùng cho Admin Report & Dashboard.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminReportResponse {
    // Thống kê người dùng
    long totalUsers;
    long totalCustomers;
    long totalManagers;
    long totalAdmins;

    // Thống kê rạp & phòng
    long totalCinemas;
    long totalRooms;

    // Thống kê booking & doanh thu
    long totalBookings;
    long confirmedBookings;
    long cancelledBookings;
    long holdBookings;
    BigDecimal totalRevenue;

    // Thống kê phim & suất chiếu
    long totalMovies;
    long totalShowtimes;
}
