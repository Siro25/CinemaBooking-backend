package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {

    Long id;
    Long movieId;
    Long customerId;
    String customerName;
    int rating;
    String content;
    LocalDateTime createdAt;
}
