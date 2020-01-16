package com.cts.codeassignment.FormSubmission.exception;

public class UserListNotFoundException extends RuntimeException {
    public UserListNotFoundException(String message) {
        super(message);
    }

    public UserListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
