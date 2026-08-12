package com.sidocinemas.cinema_booking.service;

import com.sidocinemas.cinema_booking.dto.request.BookingComboRequest;
import com.sidocinemas.cinema_booking.dto.request.ComboItemRequest;
import com.sidocinemas.cinema_booking.dto.response.BookingComboResponse;
import com.sidocinemas.cinema_booking.dto.response.BookingResponse;
import com.sidocinemas.cinema_booking.dto.response.ComboItemResponse;

import java.util.List;

public interface ComboService {

    // ── Public / Customer ────────────────────────────────────────────────────

    /** Lấy danh sách combo đang available của rạp (public endpoint) */
    List<ComboItemResponse> getAvailableComboByCinema(Long cinemaId);

    /**
     * Customer cập nhật combo cho booking đang ở trạng thái HOLD.
     * Ghi đè toàn bộ (replace), không phải append.
     * Trả về BookingResponse đã cập nhật totalPrice.
     */
    BookingResponse updateBookingCombos(Long bookingId, Long customerId, BookingComboRequest request);

    // ── Manager CRUD ─────────────────────────────────────────────────────────

    List<ComboItemResponse> getAllComboByCinema(Long cinemaId);

    ComboItemResponse getComboById(Long id);

    ComboItemResponse createCombo(Long cinemaId, ComboItemRequest request);

    ComboItemResponse updateCombo(Long id, Long cinemaId, ComboItemRequest request);

    void deleteCombo(Long id, Long cinemaId);
}
