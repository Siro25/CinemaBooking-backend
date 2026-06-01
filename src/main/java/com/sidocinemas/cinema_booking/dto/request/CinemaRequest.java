package com.sidocinemas.cinema_booking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRequest {
    @NotBlank(message = "NAME_NOT_BLANK")
    String name;

    @NotBlank(message = "ADDRESS_NOT_BLANK")
    String address;

    @NotNull(message = "MANAGER_NOT_NULL")
    Long managerId;
}