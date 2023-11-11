package com.hackathoon.datavisualizer.exception;

import org.springframework.http.HttpStatus;

public class NoResourceFoundExceptionWeb extends WebApplicationException {

    public NoResourceFoundExceptionWeb(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.NOT_FOUND;
    }

}
