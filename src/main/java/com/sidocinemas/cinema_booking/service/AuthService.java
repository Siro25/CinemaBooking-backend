package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.LoginRequest;
import com.sidocinemas.cinema_booking.dto.request.RefreshTokenRequest;
import com.sidocinemas.cinema_booking.dto.request.RegisterRequest;
import com.sidocinemas.cinema_booking.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
