package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting, Long> {
}
