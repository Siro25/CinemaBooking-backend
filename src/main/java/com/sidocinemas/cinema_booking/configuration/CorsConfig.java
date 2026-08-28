package com.sidocinemas.cinema_booking.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

        // Đọc từ biến môi trường ALLOWED_ORIGINS, mặc định cho phép localhost và các
        // domain của vercel khi dev
        @Value("${ALLOWED_ORIGINS:http://localhost,http://localhost:5173,http://localhost:3000,https://*.vercel.app}")
        private String allowedOriginsStr;

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();

                // Tách chuỗi ALLOWED_ORIGINS thành danh sách (phân cách bằng dấu phẩy)
                List<String> allowedOrigins = new java.util.ArrayList<>(Arrays.asList(allowedOriginsStr.split(",")));

                // Luôn luôn cho phép tất cả các domain của Vercel (kể cả khi biến môi trường
                // ghi đè giá trị mặc định)
                if (!allowedOrigins.contains("https://*.vercel.app")) {
                        allowedOrigins.add("https://*.vercel.app");
                }

                configuration.setAllowedOriginPatterns(allowedOrigins);

                // Allow specific methods
                configuration.setAllowedMethods(Arrays.asList(
                                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

                // Allow specific headers
                configuration.setAllowedHeaders(Arrays.asList(
                                "Authorization",
                                "Content-Type",
                                "X-Requested-With",
                                "Accept",
                                "Origin",
                                "Access-Control-Request-Method",
                                "Access-Control-Request-Headers"));

                // Expose headers
                configuration.setExposedHeaders(Arrays.asList(
                                "Access-Control-Allow-Origin",
                                "Access-Control-Allow-Credentials"));

                // Allow credentials (for JWT tokens)
                configuration.setAllowCredentials(true);

                // Max age for preflight requests
                configuration.setMaxAge(3600L);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/api/**", configuration);

                return source;
        }
}