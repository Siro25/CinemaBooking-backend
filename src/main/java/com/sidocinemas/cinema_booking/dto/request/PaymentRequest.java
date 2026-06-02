package com.sidocinemas.cinema_booking.dto.request;

import com.sidocinemas.cinema_booking.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    @NotNull(message = "BOOKING_NOT_NULL")
    Long bookingId;

    @NotNull(message = "AMOUNT_NOT_NULL")
    BigDecimal amount;

    @NotNull(message = "PAYMENT_METHOD_NOT_NULL")
    PaymentMethod method;
}