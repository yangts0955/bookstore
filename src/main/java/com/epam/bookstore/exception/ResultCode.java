package com.epam.bookstore.exception;

public enum ResultCode implements IErrorCode{
    SUCCESS(200, "success"),
    FAILED(500, "failed"),
    VALIDATE_FAILED(404, "parameter validate failed"),
    NO_ENOUGH_BOOK(404,"no enough book"),
    INCORRECT_PASSWORD(404,"incorrect password"),
    UNAUTHORIZED(401, "no login or expired token"),
    FORBIDDEN(403, "forbidden");


    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
