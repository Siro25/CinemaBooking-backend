package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingSummaryResponse {
    
    Long movieId;
    Double averageRating;
    Long totalReviews;
    
    @Builder.Default
    long[] ratingCounts = new long[5]; // ratingCounts[0] = 1 sao, ..., ratingCounts[4] = 5 sao
}
