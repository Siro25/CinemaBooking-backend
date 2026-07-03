package com.sidocinemas.cinema_booking.auth;

import com.sidocinemas.cinema_booking.domain.User;
import com.sidocinemas.cinema_booking.dto.request.LoginRequest;
import com.sidocinemas.cinema_booking.repository.UserRepository;
import com.sidocinemas.cinema_booking.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test chẩn đoán lỗi login:
 * - Kiểm tra dữ liệu user thực tế trong DB
 * - Kiểm tra BCrypt hash trong data.sql có khớp với "password" không
 * - Kiểm tra login flow hoàn chỉnh
 */
@SpringBootTest
class LoginDebugTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    // Hash này copy từ data.sql
    private static final String HASH_FROM_DATA_SQL =
            "$2a$10$N.FS3O3ihaBjMZtiSMrEPuyaOcKhLBzqjqN3.VW1knXXhTZ1OQWh6";

    @Test
    @DisplayName("1. Kiểm tra BCrypt hash trong data.sql có khớp với 'password' không")
    void testBCryptHashMatchesPassword() {
        System.out.println("\n========== TEST 1: BCrypt Hash ==========");
        System.out.println("Hash từ data.sql: " + HASH_FROM_DATA_SQL);

        boolean matches = passwordEncoder.matches("password", HASH_FROM_DATA_SQL);
        System.out.println("passwordEncoder.matches('password', hash) = " + matches);

        // Thử thêm một số mật khẩu phổ biến
        String[] candidates = {"password", "123456", "admin", "Password", "PASSWORD", "password123"};
        for (String candidate : candidates) {
            boolean m = passwordEncoder.matches(candidate, HASH_FROM_DATA_SQL);
            System.out.println("  Thử '" + candidate + "': " + (m ? "✅ KHỚP" : "❌ không khớp"));
        }

        System.out.println("==========================================\n");
        assertThat(matches)
                .as("Hash từ data.sql PHẢI khớp với 'password'")
                .isTrue();
    }

    @Test
    @DisplayName("2. Kiểm tra users có trong database không")
    void testUsersExistInDatabase() {
        System.out.println("\n========== TEST 2: Users trong DB ==========");
        List<User> allUsers = userRepository.findAll();
        System.out.println("Số lượng user trong DB: " + allUsers.size());

        if (allUsers.isEmpty()) {
            System.out.println("❌ KHÔNG CÓ USER NÀO TRONG DATABASE!");
            System.out.println("   → data.sql chưa chạy hoặc bị lỗi");
        } else {
            for (User user : allUsers) {
                System.out.println("User: " + user.getEmail()
                        + " | role=" + user.getRole()
                        + " | status=" + user.getStatus()
                        + " | passwordHash=" + user.getPassword().substring(0, 20) + "..."
                );
            }
        }
        System.out.println("=============================================\n");

        assertThat(allUsers)
                .as("Database phải có ít nhất 1 user (từ data.sql)")
                .isNotEmpty();
    }

    @Test
    @DisplayName("3. Kiểm tra user admin có trong DB và password hash khớp không")
    void testAdminUserPasswordMatch() {
        System.out.println("\n========== TEST 3: Admin Password ==========");
        String adminEmail = "admin@sidocinemas.com";
        Optional<User> adminOpt = userRepository.findByEmail(adminEmail);

        if (adminOpt.isEmpty()) {
            System.out.println("❌ Không tìm thấy user: " + adminEmail);
            System.out.println("   → Kiểm tra lại data.sql có chạy không");
        } else {
            User admin = adminOpt.get();
            System.out.println("✅ Tìm thấy user: " + adminEmail);
            System.out.println("   Role: " + admin.getRole());
            System.out.println("   Status: " + admin.getStatus());
            System.out.println("   Hash đầy đủ: " + admin.getPassword());

            boolean pwMatch = passwordEncoder.matches("password", admin.getPassword());
            System.out.println("   matches('password'): " + (pwMatch ? "✅ ĐÚNG" : "❌ SAI"));

            if (!pwMatch) {
                System.out.println("\n   ⚠️ Password không khớp! Thử debug:");
                // Tạo hash mới từ "password" và in ra để so sánh
                String newHash = passwordEncoder.encode("password");
                System.out.println("   Hash MỚI từ 'password': " + newHash);
                System.out.println("   Hash TRONG DB:           " + admin.getPassword());
                System.out.println("   Chúng có khác nhau không? " + !admin.getPassword().equals(newHash));
            }
        }
        System.out.println("============================================\n");

        assertThat(adminOpt).as("Admin user phải tồn tại trong DB").isPresent();
        assertThat(passwordEncoder.matches("password", adminOpt.get().getPassword()))
                .as("Password 'password' phải khớp với hash của admin trong DB")
                .isTrue();
    }

    @Test
    @DisplayName("4. Test login flow hoàn chỉnh qua AuthService")
    void testLoginFlowWithAuthService() {
        System.out.println("\n========== TEST 4: Login Flow ==========");
        String email = "admin@sidocinemas.com";
        String password = "password";

        try {
            System.out.println("Thử login với: " + email + " / " + password);
            var response = authService.login(
                    LoginRequest.builder()
                            .email(email)
                            .password(password)
                            .build()
            );
            System.out.println("✅ Login THÀNH CÔNG!");
            System.out.println("   accessToken: " + response.getAccessToken().substring(0, 30) + "...");
            System.out.println("   role: " + response.getRole());
            System.out.println("   fullName: " + response.getFullName());
            assertThat(response.getAccessToken()).isNotBlank();
        } catch (Exception e) {
            System.out.println("❌ Login THẤT BẠI: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            throw e;
        }
        System.out.println("=========================================\n");
    }
}
