package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.BookingComboRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.service.ComboService;
import com.sidocinemas.cinema_booking.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Customer Combo Controller
 * Endpoint: PATCH /api/v1/customer/bookings/{bookingId}/combos
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/customer/bookings")
@RequiredArgsConstructor
public class CustomerComboController {

    private final ComboService comboService;

    /**
     * Cập nhật (ghi đè) danh sách combo đã chọn cho một booking.
     * Chỉ áp dụng khi booking đang ở trạng thái HOLD.
     * totalPrice của booking sẽ được tính lại = vé + combo.
     */
    @PatchMapping("/{bookingId}/combos")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<BookingResponse> updateBookingCombos(
            @PathVariable Long bookingId,
            @Valid @RequestBody BookingComboRequest request) {
        Long customerId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Updating combos for bookingId={}, customerId={}", bookingId, customerId);
        return ApiResponse.<BookingResponse>builder()
                .data(comboService.updateBookingCombos(bookingId, customerId, request))
                .message("Cập nhật combo thành công")
                .build();
    }
}
