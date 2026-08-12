package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingComboResponse {

    Long id;
    Long comboItemId;
    String comboName;
    String comboDescription;
    String comboImageUrl;
    BigDecimal unitPrice;
    int quantity;
    BigDecimal subtotal;
}
