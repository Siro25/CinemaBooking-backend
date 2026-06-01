package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.RoomType;
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
public class RoomResponse {
    Long id;
    String roomNumber;
    RoomType type;
    Integer capacity;
    Long cinemaId;
    String cinemaName;
}