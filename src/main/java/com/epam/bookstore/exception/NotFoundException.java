package com.epam.bookstore.exception;

public class NotFoundException extends httpException {
    public NotFoundException(int code) {
        this.httpStatusCode = 404;
        this.code = code;
    }


}