package com.sidocinemas.cinema_booking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComboItemRequest {

    @NotBlank(message = "Tên combo không được để trống")
    String name;

    String description;

    @NotNull(message = "Giá combo không được để trống")
    @PositiveOrZero(message = "Giá combo phải >= 0")
    BigDecimal price;

    String imageUrl;

    @Builder.Default
    Boolean isAvailable = true;
}
