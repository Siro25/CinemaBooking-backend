package com.sidocinemas.cinema_booking.repository;

import com.sidocinemas.cinema_booking.domain.ComboItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboItemRepository extends JpaRepository<ComboItem, Long> {
    List<ComboItem> findByCinemaIdAndIsAvailableTrue(Long cinemaId);

    List<ComboItem> findByCinemaId(Long cinemaId);
}
