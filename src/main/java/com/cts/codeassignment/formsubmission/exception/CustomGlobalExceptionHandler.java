package com.cts.codeassignment.formsubmission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userHandler(Exception ex, WebRequest request) throws IOException {
        ErrorMessage error=new ErrorMessage();
        error.setTimestamp(LocalDateTime.now());
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserListNotFoundException.class)
    public ResponseEntity<ErrorMessage> userListHandler(Exception ex, WebRequest request) throws IOException{
        ErrorMessage error=new ErrorMessage();
        error.setTimestamp(LocalDateTime.now());
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity<ErrorMessage> userDetailHandler(Exception ex, WebRequest request) throws IOException{
        ErrorMessage error=new ErrorMessage();
        error.setTimestamp(LocalDateTime.now());
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }
}

