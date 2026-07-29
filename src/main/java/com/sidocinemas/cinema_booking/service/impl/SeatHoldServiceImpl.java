package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.service.SeatHoldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatHoldServiceImpl implements SeatHoldService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${seat-hold.ttl-minutes:10}")
    private long ttlMinutes;

    // Key format: seat:hold:{showtimeId}:{seatId}
    private static final String KEY_PREFIX = "seat:hold:";

    private String buildKey(Long showtimeId, Long seatId) {
        return KEY_PREFIX + showtimeId + ":" + seatId;
    }

    @Override
    public void holdSeats(Long showtimeId, List<Long> seatIds, Long userId) {
        // Kiểm tra trước khi giữ: có ghế nào bị người khác hold không?
        for (Long seatId : seatIds) {
            String key = buildKey(showtimeId, seatId);
            Object existingUserId = redisTemplate.opsForValue().get(key);
            if (existingUserId != null && !existingUserId.toString().equals(userId.toString())) {
                log.warn("[SeatHold] Seat {}:{} is held by user {}, requested by user {}",
                        showtimeId, seatId, existingUserId, userId);
                throw new AppException(ErrorCode.SEAT_HELD_BY_ANOTHER_USER);
            }
        }

        // Giữ tất cả ghế trong Redis với TTL
        for (Long seatId : seatIds) {
            String key = buildKey(showtimeId, seatId);
            redisTemplate.opsForValue().set(key, userId.toString(), ttlMinutes, TimeUnit.MINUTES);
            log.info("[SeatHold] Held seat {}:{} for user {} (TTL: {} min)", showtimeId, seatId, userId, ttlMinutes);
        }
    }

    @Override
    public void releaseSeats(Long showtimeId, List<Long> seatIds) {
        for (Long seatId : seatIds) {
            String key = buildKey(showtimeId, seatId);
            Boolean deleted = redisTemplate.delete(key);
            if (Boolean.TRUE.equals(deleted)) {
                log.info("[SeatHold] Released seat {}:{} from Redis", showtimeId, seatId);
            }
        }
    }

    @Override
    public boolean isAnyHeldByOther(Long showtimeId, List<Long> seatIds, Long userId) {
        for (Long seatId : seatIds) {
            String key = buildKey(showtimeId, seatId);
            Object existingUserId = redisTemplate.opsForValue().get(key);
            if (existingUserId != null && !existingUserId.toString().equals(userId.toString())) {
                log.debug("[SeatHold] Seat {}:{} held by another user: {}", showtimeId, seatId, existingUserId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isHoldStillValid(Long showtimeId, List<Long> seatIds, Long userId) {
        if (seatIds == null || seatIds.isEmpty()) return false;
        for (Long seatId : seatIds) {
            String key = buildKey(showtimeId, seatId);
            Object existingUserId = redisTemplate.opsForValue().get(key);
            if (existingUserId != null && existingUserId.toString().equals(userId.toString())) {
                return true; // Ít nhất 1 ghế còn valid
            }
        }
        return false;
    }
}
