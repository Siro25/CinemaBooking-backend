package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerReportResponse {
    Long cinemaId;
    String cinemaName;
    BigDecimal totalRevenue;
    long totalBookings;
    long confirmedBookings;
    long cancelledBookings;
    long holdBookings;

    List<MovieRevenueResponse> revenueByMovie;
}
