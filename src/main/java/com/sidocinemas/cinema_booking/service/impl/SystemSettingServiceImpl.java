package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.SystemSetting;
import com.sidocinemas.cinema_booking.dto.request.SystemSettingRequest;
import com.sidocinemas.cinema_booking.dto.response.SystemSettingResponse;
import com.sidocinemas.cinema_booking.repository.SystemSettingRepository;
import com.sidocinemas.cinema_booking.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemSettingServiceImpl implements SystemSettingService {

    private final SystemSettingRepository settingRepository;
    private static final Long SETTING_ID = 1L;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initSettings() {
        if (!settingRepository.existsById(SETTING_ID)) {
            SystemSetting defaultSetting = SystemSetting.builder()
                    .id(SETTING_ID)
                    .siteName("CinePass")
                    .contactEmail("support@cinepass.vn")
                    .supportPhone("1900 1234")
                    .maxTicketsPerBooking(10)
                    .cancellationTimeLimit(60)
                    .enableStripe(true)
                    .enableMomo(true)
                    .enableZaloPay(true)
                    .maintenanceMode(false)
                    .requireEmailVerification(true)
                    .build();
            settingRepository.save(defaultSetting);
            log.info("[SYSTEM] Initialized default system settings in database");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SystemSettingResponse getSettings() {
        SystemSetting setting = settingRepository.findById(SETTING_ID)
                .orElseThrow(() -> new IllegalStateException("System settings not initialized"));
        return mapToResponse(setting);
    }

    @Override
    @Transactional
    public SystemSettingResponse updateSettings(SystemSettingRequest request) {
        SystemSetting setting = settingRepository.findById(SETTING_ID)
                .orElseThrow(() -> new IllegalStateException("System settings not initialized"));
        
        setting.setSiteName(request.getSiteName());
        setting.setContactEmail(request.getContactEmail());
        setting.setSupportPhone(request.getSupportPhone());
        setting.setMaxTicketsPerBooking(request.getMaxTicketsPerBooking());
        setting.setCancellationTimeLimit(request.getCancellationTimeLimit());
        setting.setEnableStripe(request.isEnableStripe());
        setting.setEnableMomo(request.isEnableMomo());
        setting.setEnableZaloPay(request.isEnableZaloPay());
        setting.setMaintenanceMode(request.isMaintenanceMode());
        setting.setRequireEmailVerification(request.isRequireEmailVerification());

        SystemSetting saved = settingRepository.save(setting);
        log.info("[ADMIN] Updated system settings");
        return mapToResponse(saved);
    }

    private SystemSettingResponse mapToResponse(SystemSetting setting) {
        return SystemSettingResponse.builder()
                .siteName(setting.getSiteName())
                .contactEmail(setting.getContactEmail())
                .supportPhone(setting.getSupportPhone())
                .maxTicketsPerBooking(setting.getMaxTicketsPerBooking())
                .cancellationTimeLimit(setting.getCancellationTimeLimit())
                .enableStripe(setting.isEnableStripe())
                .enableMomo(setting.isEnableMomo())
                .enableZaloPay(setting.isEnableZaloPay())
                .maintenanceMode(setting.isMaintenanceMode())
                .requireEmailVerification(setting.isRequireEmailVerification())
                .build();
    }
}
