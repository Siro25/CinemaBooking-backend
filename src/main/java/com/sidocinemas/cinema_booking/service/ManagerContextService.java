package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.domain.Cinema;
import com.sidocinemas.cinema_booking.domain.User;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.CinemaRepository;
import com.sidocinemas.cinema_booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Utility service giúp Manager controllers lấy thông tin
 * cinema mà manager hiện tại đang phụ trách.
 */
@Service
@RequiredArgsConstructor
public class ManagerContextService {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;

    /**
     * Trả về cinemaId của Manager đang đăng nhập.
     * Ném exception nếu manager chưa được gán rạp.
     */
    @Transactional(readOnly = true)
    public Long getCurrentManagerCinemaId() {
        return getCurrentManagerCinema().getId();
    }

    /**
     * Trả về Cinema entity của Manager đang đăng nhập.
     */
    @Transactional(readOnly = true)
    public Cinema getCurrentManagerCinema() {
        String email = getCurrentEmail();

        User manager = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (manager.getCinema() == null) {
            throw new AppException(ErrorCode.CINEMA_NOT_FOUND);
        }

        return manager.getCinema();
    }

    private String getCurrentEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return auth.getName(); // username = email (set bởi JwtAuthenticationFilter)
    }
}
