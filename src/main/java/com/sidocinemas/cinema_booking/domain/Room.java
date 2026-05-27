package com.sidocinemas.cinema_booking.domain;

import com.sidocinemas.cinema_booking.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "room_number", nullable = false)
    String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RoomType type;

    @Column(nullable = false)
    Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    Cinema cinema;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<Seat> seats = new ArrayList<>();
}
