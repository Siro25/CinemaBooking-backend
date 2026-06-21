package com.sidocinemas.cinema_booking.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid request", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Email này đã được sử dụng", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "Email hoặc mật khẩu không chính xác", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1004, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1005, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN),
    USER_NOT_ACTIVE(1006, "Tài khoản của bạn đã bị khóa hoặc chưa kích hoạt", HttpStatus.FORBIDDEN),
    INVALID_PASSWORD(1007, "Email hoặc mật khẩu không chính xác", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1008, "Không tìm thấy người dùng", HttpStatus.NOT_FOUND),
    MOVIE_NOT_FOUND(1009, "Không tìm thấy phim", HttpStatus.NOT_FOUND),
    CINEMA_NOT_FOUND(1010, "Không tìm thấy rạp", HttpStatus.NOT_FOUND),
    ROOM_NOT_FOUND(1011, "Không tìm thấy phòng chiếu", HttpStatus.NOT_FOUND),
    PAYMENT_NOT_FOUND(1012, "Không tìm thấy giao dịch thanh toán", HttpStatus.NOT_FOUND),
    BOOKING_NOT_FOUND(1013, "Không tìm thấy đặt vé", HttpStatus.NOT_FOUND),
    PAYMENT_ALREADY_EXISTS_FOR_BOOKING(1014, "Giao dịch thanh toán cho vé này đã tồn tại", HttpStatus.BAD_REQUEST),
    SHOWTIME_NOT_FOUND(1015, "Không tìm thấy suất chiếu", HttpStatus.NOT_FOUND),
    SHOWTIME_OVERLAP(1016, "Suất chiếu bị trùng lịch với suất chiếu khác trong cùng phòng", HttpStatus.CONFLICT),
    SHOWTIME_END_BEFORE_START(1017, "Thời gian kết thúc phải sau thời gian bắt đầu", HttpStatus.BAD_REQUEST),
    SEAT_NOT_FOUND(1018, "Không tìm thấy ghế", HttpStatus.NOT_FOUND),
    SEAT_ALREADY_BOOKED(1019, "Một hoặc nhiều ghế đã được đặt", HttpStatus.CONFLICT),
    SEAT_NOT_BELONG_TO_ROOM(1020, "Ghế không thuộc phòng chiếu của suất chiếu này", HttpStatus.BAD_REQUEST),
    BOOKING_CANNOT_CANCEL(1021, "Chỉ có thể huỷ đặt vé ở trạng thái HOLD hoặc CONFIRMED", HttpStatus.BAD_REQUEST),
    BOOKING_ALREADY_CONFIRMED(1022, "Đặt vé đã được xác nhận", HttpStatus.BAD_REQUEST),
    NO_SEATS_SELECTED(1023, "Vui lòng chọn ít nhất một ghế", HttpStatus.BAD_REQUEST),
    POSTER_NOT_FOUND(1024, "Không tìm thấy ảnh poster", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
