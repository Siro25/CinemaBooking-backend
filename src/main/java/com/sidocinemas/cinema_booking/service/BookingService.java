package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.BookingRequest;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.dto.response.ManagerReportResponse;

import java.util.List;

public interface BookingService {

    BookingResponse createBooking(Long customerId, BookingRequest request);

    BookingResponse confirmBooking(Long bookingId);

    BookingResponse cancelBooking(Long bookingId);

    BookingResponse getBookingById(Long id);

    List<BookingResponse> getBookingsByCustomer(Long customerId);

    List<BookingResponse> getAllBookings();

    List<BookingResponse> getBookingsByCinema(Long cinemaId);

    ManagerReportResponse getManagerReport(Long cinemaId);
}
