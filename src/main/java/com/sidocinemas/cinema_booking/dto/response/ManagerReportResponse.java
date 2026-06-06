package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

/**
 * Báo cáo doanh thu của một rạp – dùng cho Manager Report.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerReportResponse {
    Long cinemaId;
    String cinemaName;

    // Tổng quan
    BigDecimal totalRevenue;
    long totalBookings;
    long confirmedBookings;
    long cancelledBookings;
    long holdBookings;

    // Chi tiết theo phim
    List<MovieRevenueResponse> revenueByMovie;
}
