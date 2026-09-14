package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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
