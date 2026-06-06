package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.User;
import com.sidocinemas.cinema_booking.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    // Admin: tìm user theo role
    List<User> findByRole(Role role);

    // Admin dashboard: đếm số user theo role
    long countByRole(Role role);
}

