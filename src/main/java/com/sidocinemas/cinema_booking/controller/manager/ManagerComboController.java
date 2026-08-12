package com.sidocinemas.cinema_booking.controller.manager;

import com.sidocinemas.cinema_booking.dto.request.ComboItemRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.ComboItemResponse;
import com.sidocinemas.cinema_booking.service.ComboService;
import com.sidocinemas.cinema_booking.service.ManagerContextService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MANAGER: Quản lý danh sách Combo bắp nước của rạp mình.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/manager/combos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('MANAGER')")
public class ManagerComboController {

    private final ComboService comboService;
    private final ManagerContextService managerContext;

    @GetMapping
    public ApiResponse<List<ComboItemResponse>> getAllCombos() {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Getting all combos for cinemaId={}", cinemaId);
        return ApiResponse.<List<ComboItemResponse>>builder()
                .data(comboService.getAllComboByCinema(cinemaId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ComboItemResponse> getComboById(@PathVariable Long id) {
        log.info("[MANAGER] Getting combo id={}", id);
        return ApiResponse.<ComboItemResponse>builder()
                .data(comboService.getComboById(id))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ComboItemResponse> createCombo(@Valid @RequestBody ComboItemRequest request) {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Creating combo for cinemaId={}", cinemaId);
        return ApiResponse.<ComboItemResponse>builder()
                .data(comboService.createCombo(cinemaId, request))
                .message("Tạo combo thành công")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ComboItemResponse> updateCombo(
            @PathVariable Long id,
            @Valid @RequestBody ComboItemRequest request) {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Updating combo id={}", id);
        return ApiResponse.<ComboItemResponse>builder()
                .data(comboService.updateCombo(id, cinemaId, request))
                .message("Cập nhật combo thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCombo(@PathVariable Long id) {
        Long cinemaId = managerContext.getCurrentManagerCinemaId();
        log.info("[MANAGER] Deleting combo id={}", id);
        comboService.deleteCombo(id, cinemaId);
    }
}
