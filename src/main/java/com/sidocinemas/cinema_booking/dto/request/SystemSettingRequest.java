package com.sidocinemas.cinema_booking.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemSettingRequest {
    String siteName;
    String contactEmail;
    String supportPhone;
    int maxTicketsPerBooking;
    int cancellationTimeLimit;
    boolean enableStripe;
    boolean enableMomo;
    boolean enableZaloPay;
    boolean maintenanceMode;
    boolean requireEmailVerification;
}
