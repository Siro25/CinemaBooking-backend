package com.sidocinemas.cinema_booking.controller.public_;

import com.sidocinemas.cinema_booking.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Public Health Controller - Health check và system info
 * Endpoint: /api/v1/public/health
 */
@RestController
@RequestMapping("/api/v1/public/health")
@RequiredArgsConstructor
public class PublicHealthController {

    /**
     * Health check endpoint
     */
    @GetMapping
    public ApiResponse<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("service", "Cinema Booking Backend");
        health.put("version", "1.0.0");
        
        return ApiResponse.<Map<String, Object>>builder()
                .data(health)
                .message("Service is running")
                .build();
    }

    /**
     * API endpoints mapping - Để frontend biết có những API nào
     */
    @GetMapping("/endpoints")
    public ApiResponse<Map<String, Object>> getApiEndpoints() {
        Map<String, Object> endpoints = new HashMap<>();
        
        // Public endpoints
        Map<String, String> publicEndpoints = new HashMap<>();
        publicEndpoints.put("auth", "/api/v1/public/auth");
        publicEndpoints.put("movies", "/api/v1/public/movies");
        publicEndpoints.put("cinemas", "/api/v1/public/cinemas");
        publicEndpoints.put("showtimes", "/api/v1/public/showtimes");
        publicEndpoints.put("seats", "/api/v1/public/seats");
        endpoints.put("public", publicEndpoints);
        
        // Customer endpoints
        Map<String, String> customerEndpoints = new HashMap<>();
        customerEndpoints.put("bookings", "/api/v1/customer/bookings");
        customerEndpoints.put("payments", "/api/v1/customer/payments");
        customerEndpoints.put("profile", "/api/v1/customer/profile");
        endpoints.put("customer", customerEndpoints);
        
        // Manager endpoints
        Map<String, String> managerEndpoints = new HashMap<>();
        managerEndpoints.put("movies", "/api/v1/manager/movies");
        managerEndpoints.put("showtimes", "/api/v1/manager/showtimes");
        managerEndpoints.put("rooms", "/api/v1/manager/rooms");
        managerEndpoints.put("bookings", "/api/v1/manager/bookings");
        managerEndpoints.put("reports", "/api/v1/manager/reports");
        endpoints.put("manager", managerEndpoints);
        
        // Admin endpoints
        Map<String, String> adminEndpoints = new HashMap<>();
        adminEndpoints.put("users", "/api/v1/admin/users");
        adminEndpoints.put("cinemas", "/api/v1/admin/cinemas");
        adminEndpoints.put("seats", "/api/v1/admin/seats");
        adminEndpoints.put("roles", "/api/v1/admin/roles");
        adminEndpoints.put("dashboard", "/api/v1/admin/dashboard");
        adminEndpoints.put("reports", "/api/v1/admin/reports");
        endpoints.put("admin", adminEndpoints);
        
        return ApiResponse.<Map<String, Object>>builder()
                .data(endpoints)
                .message("Available API endpoints")
                .build();
    }
}