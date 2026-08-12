package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.ComboItemResponse;
import com.sidocinemas.cinema_booking.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Public Combo Controller - Không cần xác thực
 * Endpoint: GET /api/v1/public/combos?cinemaId={cinemaId}
 */
@RestController
@RequestMapping("/api/v1/public/combos")
@RequiredArgsConstructor
public class PublicComboController {

    private final ComboService comboService;

    /**
     * Lấy danh sách combo đang bán của một rạp cụ thể.
     * Frontend gọi khi hiển thị ComboModal sau khi chọn ghế.
     */
    @GetMapping
    public ApiResponse<List<ComboItemResponse>> getAvailableCombos(@RequestParam Long cinemaId) {
        return ApiResponse.<List<ComboItemResponse>>builder()
                .data(comboService.getAvailableComboByCinema(cinemaId))
                .build();
    }
}
