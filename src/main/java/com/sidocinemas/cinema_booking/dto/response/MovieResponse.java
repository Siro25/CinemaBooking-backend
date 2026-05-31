package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.AgeRating;
import com.sidocinemas.cinema_booking.enums.MovieStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    Long id;
    String title;
    String description;
    Integer duration;
    String genre;
    AgeRating ageRating;
    String posterUrl;
    MovieStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
