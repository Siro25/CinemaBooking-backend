package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.request.LoginRequest;
import com.sidocinemas.cinema_booking.dto.request.RefreshTokenRequest;
import com.sidocinemas.cinema_booking.dto.request.RegisterRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.AuthResponse;
import com.sidocinemas.cinema_booking.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Public Auth Controller - Authentication endpoints
 * Endpoint: /api/v1/public/auth
 */
@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
public class PublicAuthController {

    private final AuthService authService;

    /**
     * Đăng nhập
     */
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ApiResponse.<AuthResponse>builder()
                .data(response)
                .message("Đăng nhập thành công")
                .build();
    }

    /**
     * Đăng ký tài khoản mới
     */
    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ApiResponse.<AuthResponse>builder()
                .data(response)
                .message("Đăng ký tài khoản thành công")
                .build();
    }

    /**
     * Refresh Token
     */
    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        AuthResponse response = authService.refreshToken(request);
        return ApiResponse.<AuthResponse>builder()
                .data(response)
                .message("Làm mới token thành công")
                .build();
    }
}