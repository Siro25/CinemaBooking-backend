package com.sidocinemas.cinema_booking.dto.response;

import com.sidocinemas.cinema_booking.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    String accessToken;
    String refreshToken;
    String email;
    String fullName;
    Role role;
}
