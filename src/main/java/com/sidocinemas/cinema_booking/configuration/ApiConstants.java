package com.sidocinemas.cinema_booking.configuration;

/**
 * API Constants - Định nghĩa các namespace path cho controller
 */
public final class ApiConstants {
    
    private ApiConstants() {
        // Utility class
    }
    
    // Base API version
    public static final String API_V1 = "/api/v1";
    
    // Public endpoints - Không cần authentication
    public static final String PUBLIC_BASE = API_V1 + "/public";
    public static final String PUBLIC_AUTH = PUBLIC_BASE + "/auth";
    public static final String PUBLIC_MOVIES = PUBLIC_BASE + "/movies";
    public static final String PUBLIC_CINEMAS = PUBLIC_BASE + "/cinemas";
    public static final String PUBLIC_SHOWTIMES = PUBLIC_BASE + "/showtimes";
    public static final String PUBLIC_SEATS = PUBLIC_BASE + "/seats";
    
    // Customer endpoints - ROLE_CUSTOMER required
    public static final String CUSTOMER_BASE = API_V1 + "/customer";
    public static final String CUSTOMER_BOOKINGS = CUSTOMER_BASE + "/bookings";
    public static final String CUSTOMER_PAYMENTS = CUSTOMER_BASE + "/payments";
    public static final String CUSTOMER_PROFILE = CUSTOMER_BASE + "/profile";
    
    // Manager endpoints - ROLE_MANAGER required
    public static final String MANAGER_BASE = API_V1 + "/manager";
    public static final String MANAGER_MOVIES = MANAGER_BASE + "/movies";
    public static final String MANAGER_SHOWTIMES = MANAGER_BASE + "/showtimes";
    public static final String MANAGER_ROOMS = MANAGER_BASE + "/rooms";
    public static final String MANAGER_BOOKINGS = MANAGER_BASE + "/bookings";
    public static final String MANAGER_REPORTS = MANAGER_BASE + "/reports";
    
    // Admin endpoints - ROLE_ADMIN required
    public static final String ADMIN_BASE = API_V1 + "/admin";
    public static final String ADMIN_USERS = ADMIN_BASE + "/users";
    public static final String ADMIN_CINEMAS = ADMIN_BASE + "/cinemas";
    public static final String ADMIN_SEATS = ADMIN_BASE + "/seats";
    public static final String ADMIN_ROLES = ADMIN_BASE + "/roles";
    public static final String ADMIN_DASHBOARD = ADMIN_BASE + "/dashboard";
    public static final String ADMIN_REPORTS = ADMIN_BASE + "/reports";
}