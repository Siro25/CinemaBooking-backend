package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComboItemResponse {

    Long id;
    Long cinemaId;
    String cinemaName;
    String name;
    String description;
    BigDecimal price;
    String imageUrl;
    Boolean isAvailable;
}
