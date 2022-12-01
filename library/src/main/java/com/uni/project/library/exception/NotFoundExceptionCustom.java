package com.uni.project.library.exception;

public class NotFoundExceptionCustom extends RuntimeException {

    public NotFoundExceptionCustom(String message) {
        super(message);
    }

    public NotFoundExceptionCustom(String msg, Throwable cause) {
        super(msg, cause);
    }
}
