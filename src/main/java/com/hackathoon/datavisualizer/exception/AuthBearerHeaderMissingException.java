package com.hackathoon.datavisualizer.exception;

public class AuthBearerHeaderMissingException extends AuthTokenMissingException {

    public AuthBearerHeaderMissingException(String message) {
        super(message);
    }

}
