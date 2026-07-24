package com.sidocinemas.cinema_booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "system_settings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemSetting {

    @Id
    Long id;

    String siteName;
    String contactEmail;
    String supportPhone;
    
    int maxTicketsPerBooking;
    int cancellationTimeLimit; // in minutes

    boolean enableStripe;
    boolean enableMomo;
    boolean enableZaloPay;

    boolean maintenanceMode;
    boolean requireEmailVerification;
}
