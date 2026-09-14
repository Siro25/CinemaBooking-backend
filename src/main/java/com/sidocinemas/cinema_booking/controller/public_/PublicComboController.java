package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.ComboItemResponse;
import com.sidocinemas.cinema_booking.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/combos")
@RequiredArgsConstructor
public class PublicComboController {

    private final ComboService comboService;

    @GetMapping
    public ApiResponse<List<ComboItemResponse>> getAvailableCombos(@RequestParam Long cinemaId) {
        return ApiResponse.<List<ComboItemResponse>>builder()
                .data(comboService.getAvailableComboByCinema(cinemaId))
                .build();
    }
}
