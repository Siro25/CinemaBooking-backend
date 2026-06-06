package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * Doanh thu theo từng phim (dùng trong Manager Report).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRevenueResponse {
    String movieTitle;
    BigDecimal totalRevenue;
    long totalBookings;
}
