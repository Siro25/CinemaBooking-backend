package com.sidocinemas.cinema_booking.dto.request;

import com.sidocinemas.cinema_booking.enums.Role;
import com.sidocinemas.cinema_booking.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @NotBlank(message = "Họ tên không được để trống")
    String fullName;

    @NotNull(message = "Trạng thái không được để trống")
    UserStatus status;

    @NotNull(message = "Vai trò không được để trống")
    Role role;
}
