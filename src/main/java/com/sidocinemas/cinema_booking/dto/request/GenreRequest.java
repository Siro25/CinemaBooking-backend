package com.sidocinemas.cinema_booking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreRequest {

    @NotBlank(message = "Tên thể loại không được để trống")
    String name;

    String description;
}
