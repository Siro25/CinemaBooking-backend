package com.sidocinemas.cinema_booking.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utility class cho việc xử lý Security Context
 * TODO: Implement JWT token parsing để lấy user ID thực tế
 */
public class SecurityUtils {

    /**
     * Lấy user ID từ JWT token trong SecurityContext
     * TODO: Hiện tại return hardcode value, cần implement JWT parsing
     */
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // TODO: Parse JWT token để lấy user ID thực tế
            // Tạm thời return 1L để test
            return 1L;
        }
        throw new RuntimeException("No authenticated user found");
    }

    /**
     * Lấy username từ SecurityContext
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }

    /**
     * Kiểm tra user hiện tại có role cụ thể không
     */
    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
        }
        return false;
    }

    /**
     * Kiểm tra user có phải là owner của resource không
     * Dùng để kiểm tra customer chỉ có thể truy cập data của chính mình
     */
    public static boolean isResourceOwner(Long resourceUserId) {
        Long currentUserId = getCurrentUserId();
        return currentUserId != null && currentUserId.equals(resourceUserId);
    }
}