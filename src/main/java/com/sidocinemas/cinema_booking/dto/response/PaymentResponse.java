package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.PaymentMethod;
import com.sidocinemas.cinema_booking.enums.PaymentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    Long id;
    Long bookingId;
    BigDecimal amount;
    PaymentMethod method;
    PaymentStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}