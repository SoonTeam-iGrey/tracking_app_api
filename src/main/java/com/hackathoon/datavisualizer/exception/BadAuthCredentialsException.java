package com.hackathoon.datavisualizer.exception;

import org.springframework.http.HttpStatus;

public class BadAuthCredentialsException extends WebApplicationException {

    public BadAuthCredentialsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

}
