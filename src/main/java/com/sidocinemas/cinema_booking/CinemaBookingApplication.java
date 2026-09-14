package com.sidocinemas.cinema_booking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class CinemaBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaBookingApplication.class, args);
	}

	@jakarta.annotation.PostConstruct
	public void init() {
		java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
	}

	@Bean
	CommandLineRunner fixNullVersions(JdbcTemplate jdbcTemplate) {
		return args -> {
			jdbcTemplate.execute("UPDATE seats SET version = 0 WHERE version IS NULL");
		};
	}

}
