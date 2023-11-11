package com.hackathoon.datavisualizer.exception;

public class AuthTokenMissingException extends RuntimeException {

    public AuthTokenMissingException(String message) {
        super(message);
    }

}
