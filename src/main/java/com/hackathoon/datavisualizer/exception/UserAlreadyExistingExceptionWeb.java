package com.hackathoon.datavisualizer.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistingExceptionWeb extends WebApplicationException {

    public UserAlreadyExistingExceptionWeb(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.CONFLICT;
    }

}
