package com.sidocinemas.cinema_booking.controller.admin;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.UserResponse;
import com.sidocinemas.cinema_booking.enums.Role;
import com.sidocinemas.cinema_booking.domain.User;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * ADMIN: Quản lý phân quyền Role cho người dùng.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/admin/roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminRoleController {

    private final UserRepository userRepository;

    @GetMapping
    public ApiResponse<List<Role>> getAllRoles() {
        log.info("[ADMIN] Fetching all roles");
        return ApiResponse.<List<Role>>builder()
                .data(Arrays.asList(Role.values()))
                .build();
    }

    @PatchMapping("/{userId}")
    public ApiResponse<UserResponse> assignRole(@PathVariable Long userId, @RequestParam Role role) {
        log.info("[ADMIN] Assigning role {} to user {}", role, userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        
        user.setRole(role);
        user = userRepository.save(user);
        
        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .build();
                
        return ApiResponse.<UserResponse>builder()
                .data(response)
                .message("Cập nhật quyền thành công")
                .build();
    }
}
