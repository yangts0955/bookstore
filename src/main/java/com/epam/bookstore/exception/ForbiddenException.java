package com.epam.bookstore.exception;

public class ForbiddenException extends httpException {

    public ForbiddenException(int code) {
        this.code = code;
        this.httpStatusCode = 403;
    }
}
