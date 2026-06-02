package com.sidocinemas.cinema_booking.service.impl;

import com.sidocinemas.cinema_booking.domain.Movie;
import com.sidocinemas.cinema_booking.domain.Room;
import com.sidocinemas.cinema_booking.domain.Showtime;
import com.sidocinemas.cinema_booking.dto.request.ShowtimeRequest;
import com.sidocinemas.cinema_booking.dto.response.ShowtimeResponse;
import com.sidocinemas.cinema_booking.exception.AppException;
import com.sidocinemas.cinema_booking.exception.ErrorCode;
import com.sidocinemas.cinema_booking.repository.MovieRepository;
import com.sidocinemas.cinema_booking.repository.RoomRepository;
import com.sidocinemas.cinema_booking.repository.ShowtimeRepository;
import com.sidocinemas.cinema_booking.service.ShowtimeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShowtimeServiceImpl implements ShowtimeService {

    ShowtimeRepository showtimeRepository;
    MovieRepository movieRepository;
    RoomRepository roomRepository;

    @Override
    @Transactional
    public ShowtimeResponse createShowtime(ShowtimeRequest request) {
        validateTimeRange(request);

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        checkOverlap(request.getRoomId(), request.getStartTime(), request.getEndTime(), null);

        Showtime showtime = Showtime.builder()
                .movie(movie)
                .room(room)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .basePrice(request.getBasePrice())
                .build();

        showtime = showtimeRepository.save(showtime);
        log.info("Created showtime id={} for movie='{}' in room='{}'",
                showtime.getId(), movie.getTitle(), room.getRoomNumber());
        return mapToResponse(showtime);
    }

    @Override
    @Transactional
    public ShowtimeResponse updateShowtime(Long id, ShowtimeRequest request) {
        validateTimeRange(request);

        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_FOUND));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        // Kiểm tra overlap, bỏ qua chính suất chiếu đang update
        checkOverlap(request.getRoomId(), request.getStartTime(), request.getEndTime(), id);

        showtime.setMovie(movie);
        showtime.setRoom(room);
        showtime.setStartTime(request.getStartTime());
        showtime.setEndTime(request.getEndTime());
        showtime.setBasePrice(request.getBasePrice());

        showtime = showtimeRepository.save(showtime);
        log.info("Updated showtime id={}", id);
        return mapToResponse(showtime);
    }

    @Override
    @Transactional
    public void deleteShowtime(Long id) {
        if (!showtimeRepository.existsById(id)) {
            throw new AppException(ErrorCode.SHOWTIME_NOT_FOUND);
        }
        showtimeRepository.deleteById(id);
        log.info("Deleted showtime id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public ShowtimeResponse getShowtimeById(Long id) {
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_FOUND));
        return mapToResponse(showtime);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowtimeResponse> getShowtimesByMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new AppException(ErrorCode.MOVIE_NOT_FOUND);
        }
        return showtimeRepository.findByMovieId(movieId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowtimeResponse> getShowtimesByRoom(Long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new AppException(ErrorCode.ROOM_NOT_FOUND);
        }
        return showtimeRepository.findByRoomId(roomId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowtimeResponse> getAllShowtimes() {
        return showtimeRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    /**
     * Validate endTime phải sau startTime.
     */
    private void validateTimeRange(ShowtimeRequest request) {
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new AppException(ErrorCode.SHOWTIME_END_BEFORE_START);
        }
    }

    /**
     * Kiểm tra trùng lịch trong cùng phòng chiếu.
     * excludeId: id suất chiếu đang update (null nếu là create).
     */
    private void checkOverlap(Long roomId, java.time.LocalDateTime startTime,
            java.time.LocalDateTime endTime, Long excludeId) {
        List<Showtime> overlapping = showtimeRepository
                .findOverlappingShowtimes(roomId, startTime, endTime);

        boolean hasConflict = overlapping.stream()
                .anyMatch(s -> !s.getId().equals(excludeId));

        if (hasConflict) {
            throw new AppException(ErrorCode.SHOWTIME_OVERLAP);
        }
    }

    private ShowtimeResponse mapToResponse(Showtime s) {
        Room room = s.getRoom();
        Movie movie = s.getMovie();

        return ShowtimeResponse.builder()
                .id(s.getId())
                .movieId(movie != null ? movie.getId() : null)
                .movieTitle(movie != null ? movie.getTitle() : null)
                .movieDuration(movie != null ? movie.getDuration() : null)
                .roomId(room != null ? room.getId() : null)
                .roomNumber(room != null ? room.getRoomNumber() : null)
                .cinemaId(room != null && room.getCinema() != null ? room.getCinema().getId() : null)
                .cinemaName(room != null && room.getCinema() != null ? room.getCinema().getName() : null)
                .startTime(s.getStartTime())
                .endTime(s.getEndTime())
                .basePrice(s.getBasePrice())
                .build();
    }
}
