package com.potato.passwordcracking.exception;

public class HashingException extends RuntimeException {

    public HashingException() {
        super();
    }

    public HashingException(String message) {
        super(message);
    }

    public HashingException(Throwable cause) {
        super(cause);
    }

    public HashingException(String message, Throwable cause) {
        super(message, cause);
    }

}
