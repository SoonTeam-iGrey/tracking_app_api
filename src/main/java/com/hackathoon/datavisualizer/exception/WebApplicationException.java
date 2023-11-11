package com.hackathoon.datavisualizer.exception;

import org.springframework.http.HttpStatus;

public abstract class WebApplicationException extends RuntimeException {

    public WebApplicationException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatusCode();

}
