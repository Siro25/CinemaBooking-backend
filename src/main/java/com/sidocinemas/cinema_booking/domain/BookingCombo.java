package com.sidocinemas.cinema_booking.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_combos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCombo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combo_item_id", nullable = false)
    ComboItem comboItem;

    @Column(nullable = false)
    int quantity;

    /**
     * Snapshot giá tại thời điểm đặt = comboItem.price * quantity
     * Tránh bị ảnh hưởng nếu giá combo thay đổi sau này.
     */
    @Column(nullable = false, precision = 12, scale = 2)
    BigDecimal subtotal;
}
