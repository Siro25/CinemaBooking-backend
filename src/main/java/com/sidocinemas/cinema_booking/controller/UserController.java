package com.sidocinemas.cinema_booking.controller;

import com.sidocinemas.cinema_booking.dto.request.UserUpdateRequest;
import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.UserResponse;
import com.sidocinemas.cinema_booking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .data(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUserById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUser(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .message("Xóa người dùng thành công")
                .build();
    }
}
