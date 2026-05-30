package com.sidocinemas.cinema_booking.domain;

import com.sidocinemas.cinema_booking.enums.AgeRating;
import com.sidocinemas.cinema_booking.enums.MovieStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@EntityListeners(AuditingEntityListener.class)
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

    @Column(columnDefinition = "TEXT")
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

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = MovieStatus.COMING_SOON; // Mặc định là sắp chiếu
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
