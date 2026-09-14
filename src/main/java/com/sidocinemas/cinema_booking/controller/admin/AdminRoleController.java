package com.sidocinemas.cinema_booking.controller.admin;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import com.sidocinemas.cinema_booking.dto.response.UserResponse;
import com.sidocinemas.cinema_booking.enums.Role;
import com.sidocinemas.cinema_booking.domain.User;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.domain.Cinema;
import com.sidocinemas.cinema_booking.repository.UserRepository;
import com.sidocinemas.cinema_booking.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin/roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminRoleController {

        private final UserRepository userRepository;
        private final CinemaRepository cinemaRepository;

        @GetMapping
        public ApiResponse<List<Role>> getAllRoles() {
                log.info("[ADMIN] Fetching all roles");
                return ApiResponse.<List<Role>>builder()
                                .data(Arrays.asList(Role.values()))
                                .build();
        }

        @PatchMapping("/{userId}")
        @Transactional
        public ApiResponse<UserResponse> assignRole(
                        @PathVariable Long userId,
                        @RequestParam Role role,
                        @RequestParam(required = false) Long cinemaId) {
                log.info("[ADMIN] Assigning role {} to user {}, cinemaId {}", role, userId, cinemaId);

                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

                if (role == Role.MANAGER && cinemaId != null) {
                        Cinema cinema = cinemaRepository.findById(cinemaId)
                                        .orElseThrow(() -> new AppException(ErrorCode.CINEMA_NOT_FOUND));
                        userRepository.updateRoleAndCinema(userId, role, cinema);
                } else if (role == Role.MANAGER) {
                        userRepository.updateRoleAndClearCinema(userId, role);
                } else {
                        userRepository.updateRoleAndClearCinema(userId, role);
                }

                // Reload
                user = userRepository.findById(userId)
                                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

                UserResponse response = UserResponse.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .fullName(user.getFullName())
                                .role(user.getRole())
                                .status(user.getStatus())
                                .createdAt(user.getCreatedAt())
                                .cinemaId(user.getCinema() != null ? user.getCinema().getId() : null)
                                .cinemaName(user.getCinema() != null ? user.getCinema().getName() : null)
                                .build();

                return ApiResponse.<UserResponse>builder()
                                .data(response)
                                .message("Cập nhật quyền thành công")
                                .build();
        }
}
