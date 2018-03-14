package com.example.springboot.exception;

public class ParamsInvalidException extends RuntimeException {

    public ParamsInvalidException() {
    }

    public ParamsInvalidException(String message) {
        super(message);
    }

    public ParamsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsInvalidException(Throwable cause) {
        super(cause);
    }

    public ParamsInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
