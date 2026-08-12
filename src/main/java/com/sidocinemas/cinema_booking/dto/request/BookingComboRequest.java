package com.sidocinemas.cinema_booking.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Request body khi customer gửi lên danh sách combo đã chọn.
 * Ví dụ: [{ comboItemId: 1, quantity: 2 }, { comboItemId: 3, quantity: 1 }]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingComboRequest {

    @Valid
    List<ComboSelection> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ComboSelection {

        @NotNull(message = "comboItemId không được để trống")
        Long comboItemId;

        @NotNull(message = "Số lượng không được để trống")
        Integer quantity;
    }
}
