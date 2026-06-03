package com.sidocinemas.cinema_booking.dto.request;

import com.sidocinemas.cinema_booking.enums.SeatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatRequest {

    @NotNull(message = "Room ID không được để trống")
    Long roomId;

    @NotBlank(message = "Hàng ghế không được để trống")
    @Size(max = 10, message = "Hàng ghế tối đa 10 ký tự")
    String row;

    @NotNull(message = "Số ghế không được để trống")
    @Min(value = 1, message = "Số ghế phải lớn hơn 0")
    Integer number;

    @NotNull(message = "Loại ghế không được để trống")
    SeatType type;
}
