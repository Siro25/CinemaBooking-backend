package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.SeatType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
    Long id;
    Long roomId;
    String roomNumber;
    String row;
    Integer number;
    SeatType type;
    // Trạng thái available sẽ được set khi query theo showtime
    Boolean available;
}