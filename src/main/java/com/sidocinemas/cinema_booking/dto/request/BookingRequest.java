package com.sidocinemas.cinema_booking.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {

    @NotNull(message = "Showtime ID không được để trống")
    Long showtimeId;

    @NotEmpty(message = "Vui lòng chọn ít nhất một ghế")
    List<Long> seatIds;
}
