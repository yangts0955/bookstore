package com.example.dockerandmysql.exception.core;

public class UnifyResponse {

    private String timer;
    private int code;
    private String message;
    private String request;

    public UnifyResponse(String timer, int code, String message, String request) {
        this.timer = timer;
        this.code = code;
        this.message = message;
        this.request = request;
    }

    public String getTimer() {
        return timer;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }


}
