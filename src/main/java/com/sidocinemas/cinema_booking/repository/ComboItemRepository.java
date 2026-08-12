package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.ComboItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboItemRepository extends JpaRepository<ComboItem, Long> {

    /** Lấy combo theo rạp, chỉ những combo đang available */
    List<ComboItem> findByCinemaIdAndIsAvailableTrue(Long cinemaId);

    /** Lấy tất cả combo theo rạp (cho Manager quản lý) */
    List<ComboItem> findByCinemaId(Long cinemaId);
}
