package com.epam.bookstore.exception;

public class CreateSuccess extends httpException{

        public CreateSuccess(int code){
            this.httpStatusCode = 201;
            this.code = code;
        }

}
