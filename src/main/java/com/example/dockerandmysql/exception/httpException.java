package com.example.dockerandmysql.exception;

public class httpException extends RuntimeException {

    protected Integer code;

    protected Integer httpStatusCode = 500;

    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }


}
