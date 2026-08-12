package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {

    Long id;

    // Customer info
    Long customerId;
    String customerName;
    String customerEmail;

    // Showtime info
    Long showtimeId;
    String movieTitle;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String roomNumber;
    String cinemaName;

    // Booking details
    BookingStatus status;
    BigDecimal totalPrice;
    BigDecimal comboPrice;   // Tổng tiền combo (riêng)
    LocalDateTime createdAt;

    // Tickets
    List<TicketResponse> tickets;

    // Combos
    List<BookingComboResponse> combos;
}
