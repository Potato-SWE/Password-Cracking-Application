package com.potato.passwordcracking.exception;

public class PasswordCrackingException extends RuntimeException {

    public PasswordCrackingException() {
        super();
    }

    public PasswordCrackingException(String message) {
        super(message);
    }

    public PasswordCrackingException(Throwable cause) {
        super(cause);
    }

    public PasswordCrackingException(String message, Throwable cause) {
        super(message, cause);
    }

}