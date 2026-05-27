package com.sidocinemas.cinema_booking.domain;

import com.sidocinemas.cinema_booking.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "seat_row", nullable = false, length = 10)
    String row;

    @Column(name = "seat_number", nullable = false)
    Integer number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    SeatType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    Room room;
}
