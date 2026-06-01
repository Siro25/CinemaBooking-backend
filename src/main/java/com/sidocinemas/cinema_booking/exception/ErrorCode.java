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
    ROOM_NOT_FOUND(1011, "Không tìm thấy phòng chiếu", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
