package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.Role;
import com.sidocinemas.cinema_booking.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String email;
    String fullName;
    Role role;
    UserStatus status;
    LocalDateTime createdAt;
    Long cinemaId;
    String cinemaName;
}
