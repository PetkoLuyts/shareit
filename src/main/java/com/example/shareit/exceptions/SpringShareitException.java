package com.example.shareit.exceptions;

public class SpringShareitException extends RuntimeException {
    public SpringShareitException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringShareitException(String exMessage) {
        super(exMessage);
    }
}
