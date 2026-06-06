package com.sidocinemas.cinema_booking.controller.customer;

import com.sidocinemas.cinema_booking.dto.request.UserUpdateRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.UserResponse;
import com.sidocinemas.cinema_booking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Customer Profile Controller - Quản lý thông tin cá nhân
 * Endpoint: /api/v1/customer/profile
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/customer/profile")
@RequiredArgsConstructor
public class CustomerProfileController {

    private final UserService userService;

    /**
     * Xem thông tin profile của mình
     */
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<UserResponse> getMyProfile() {
        // TODO: Lấy userId từ JWT token trong SecurityContext
        Long userId = 1L; // Tạm thời hardcode để test
        log.info("Customer getting profile");
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUserById(userId))
                .build();
    }

    /**
     * Cập nhật thông tin profile
     */
    @PutMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<UserResponse> updateProfile(@Valid @RequestBody UserUpdateRequest request) {
        // TODO: Lấy userId từ JWT token và chỉ cho phép update profile của chính mình
        Long userId = 1L; // Tạm thời hardcode để test
        log.info("Customer updating profile");
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUser(userId, request))
                .message("Cập nhật thông tin thành công")
                .build();
    }
}