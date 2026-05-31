package com.sidocinemas.cinema_booking.dto.request;

import com.sidocinemas.cinema_booking.enums.AgeRating;
import com.sidocinemas.cinema_booking.enums.MovieStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {

    @NotBlank(message = "Tên phim không được để trống")
    String title;

    String description;

    @NotNull(message = "Thời lượng phim không được để trống")
    @Min(value = 1, message = "Thời lượng phim phải lớn hơn 0")
    Integer duration;

    @NotBlank(message = "Thể loại phim không được để trống")
    String genre;

    @NotNull(message = "Độ tuổi quy định không được để trống")
    AgeRating ageRating;

    String posterUrl;

    MovieStatus status;
}
