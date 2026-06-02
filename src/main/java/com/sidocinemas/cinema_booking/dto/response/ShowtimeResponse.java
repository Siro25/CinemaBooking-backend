package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeResponse {

    Long id;

    // Movie info
    Long movieId;
    String movieTitle;
    Integer movieDuration;

    // Room info
    Long roomId;
    String roomNumber;
    Long cinemaId;
    String cinemaName;

    LocalDateTime startTime;
    LocalDateTime endTime;
    BigDecimal basePrice;
}
