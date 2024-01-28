package com.examportal.exception;

import com.examportal.payload.ApiResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    ResponseEntity<ApiResponse> handlerUserExistsException(UserExistsException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserNotExistException.class)
    ResponseEntity<ApiResponse> handlerUsersNotExistException(UserNotExistException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ApiResponse> handlerUsersNotExistException(EntityNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    ResponseEntity<ApiResponse> handlerUsersNotExistException(EntityExistsException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse> handlerUsersNotExistException(Exception ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(true).httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ApiResponse> handleImageAlreadyExistsException(IllegalArgumentException ex) {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).status(false).httpStatus(HttpStatus.CONFLICT)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
