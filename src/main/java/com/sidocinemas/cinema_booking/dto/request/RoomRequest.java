package com.sidocinemas.cinema_booking.dto.request;

import com.sidocinemas.cinema_booking.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomRequest {
    @NotBlank(message = "ROOM_NUMBER_NOT_BLANK")
    String roomNumber;

    @NotNull(message = "ROOM_TYPE_NOT_NULL")
    RoomType type;

    @NotNull(message = "CAPACITY_NOT_NULL")
    @Min(value = 1, message = "CAPACITY_MIN")
    Integer capacity;

    @NotNull(message = "CINEMA_NOT_NULL")
    Long cinemaId;
}