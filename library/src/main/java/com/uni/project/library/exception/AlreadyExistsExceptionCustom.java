package com.uni.project.library.exception;

public class AlreadyExistsExceptionCustom extends RuntimeException {

    public AlreadyExistsExceptionCustom(String message) {
        super(message);
    }

    public AlreadyExistsExceptionCustom(String msg, Throwable cause) {
        super(msg, cause);
    }
}
