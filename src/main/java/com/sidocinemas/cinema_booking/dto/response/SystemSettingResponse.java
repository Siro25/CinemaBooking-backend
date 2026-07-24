package com.sidocinemas.cinema_booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemSettingResponse {
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
