package com.sidocinemas.cinema_booking.domain;

import com.sidocinemas.cinema_booking.enums.AgeRating;
import com.sidocinemas.cinema_booking.enums.MovieStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @Column(nullable = false)
    Integer duration;

    @Column(nullable = false)
    String genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_rating", nullable = false)
    AgeRating ageRating;

    @Column(name = "poster_url")
    String posterUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    MovieStatus status;

    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = MovieStatus.COMING_SOON; // Mặc định là sắp chiếu
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
