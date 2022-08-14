package com.example.dockerandmysql.exception;

public class UnAuthenticateException extends httpException{

    public UnAuthenticateException(int code){
        this.code = code;
        this.httpStatusCode = 401;
    }
}
