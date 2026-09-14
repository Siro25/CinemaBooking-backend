package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.UserUpdateRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.UserResponse;
import com.sidocinemas.cinema_booking.service.UserService;
import com.sidocinemas.cinema_booking.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer/profile")
@RequiredArgsConstructor
public class CustomerProfileController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<UserResponse> getMyProfile() {
        Long userId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Getting profile for userId={}", userId);
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUserById(userId))
                .build();
    }

    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<UserResponse> updateProfile(@Valid @RequestBody UserUpdateRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        log.info("[CUSTOMER] Updating profile for userId={}", userId);
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUser(userId, request))
                .message("Cập nhật thông tin thành công")
                .build();
    }
}