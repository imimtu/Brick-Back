package com.im2.brickback.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid Password"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "Permission is invalid"),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is invalid"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),

    ;

    private final HttpStatus status;
    private final String message;
}
