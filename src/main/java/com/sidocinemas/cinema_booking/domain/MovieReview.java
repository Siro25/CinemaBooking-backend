package com.sidocinemas.cinema_booking.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "movie_reviews",
    uniqueConstraints = @UniqueConstraint(
        name = "uk_movie_review_customer",
        columnNames = {"movie_id", "customer_id"}
    )
)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    User customer;

    /**
     * Điểm đánh giá từ 1 đến 5 sao.
     */
    @Column(nullable = false)
    int rating;

    /**
     * Nội dung bình luận (tuỳ chọn).
     */
    @Column(columnDefinition = "TEXT")
    String content;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;
}
