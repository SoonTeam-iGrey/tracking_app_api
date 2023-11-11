package com.hackathoon.datavisualizer.exception;

public class AuthCookieMissingException extends AuthTokenMissingException {

    public AuthCookieMissingException(String message) {
        super(message);
    }

}
