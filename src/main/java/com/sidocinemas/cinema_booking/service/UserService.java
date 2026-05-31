package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.UserUpdateRequest;
import com.sidocinemas.cinema_booking.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UserUpdateRequest request);
    void deleteUser(Long id);
}
