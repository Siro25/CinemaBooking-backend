package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.dto.response.AdminReportResponse;
import com.sidocinemas.cinema_booking.enums.BookingStatus;
import com.sidocinemas.cinema_booking.enums.Role;
import com.sidocinemas.cinema_booking.repository.*;
import com.sidocinemas.cinema_booking.service.AdminReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminReportServiceImpl implements AdminReportService {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;

    @Override
    @Transactional(readOnly = true)
    public AdminReportResponse getSystemReport() {
        log.info("Generating system-wide admin report");

        return AdminReportResponse.builder()
                // Users
                .totalUsers(userRepository.count())
                .totalCustomers(userRepository.countByRole(Role.CUSTOMER))
                .totalManagers(userRepository.countByRole(Role.MANAGER))
                .totalAdmins(userRepository.countByRole(Role.ADMIN))
                // Cinemas & Rooms
                .totalCinemas(cinemaRepository.count())
                .totalRooms(roomRepository.count())
                // Bookings
                .totalBookings(bookingRepository.count())
                .confirmedBookings(bookingRepository.countByStatus(BookingStatus.CONFIRMED))
                .cancelledBookings(bookingRepository.countByStatus(BookingStatus.CANCELLED))
                .holdBookings(bookingRepository.countByStatus(BookingStatus.HOLD))
                .totalRevenue(bookingRepository.sumTotalRevenue())
                // Movies & Showtimes
                .totalMovies(movieRepository.count())
                .totalShowtimes(showtimeRepository.count())
                .build();
    }
}
