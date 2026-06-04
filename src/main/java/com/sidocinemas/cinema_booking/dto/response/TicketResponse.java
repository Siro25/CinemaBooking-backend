package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.SeatType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {

    Long id;
    Long seatId;
    String seatRow;
    Integer seatNumber;
    SeatType seatType;
    BigDecimal price;
    String qrCode; // raw string UUID
    String qrCodeBase64; // base64 encoded image
}
