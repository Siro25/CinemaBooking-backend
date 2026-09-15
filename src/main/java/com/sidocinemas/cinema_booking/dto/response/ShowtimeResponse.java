package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    Long id;

    // Movie
    Long movieId;
    String movieTitle;
    Integer movieDuration;

    // Room
    Long roomId;
    String roomNumber;
    Long cinemaId;
    String cinemaName;

    LocalDateTime startTime;
    LocalDateTime endTime;
    BigDecimal basePrice;
}
