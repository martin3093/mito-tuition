package com.mitocode.mitotuition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleDefaultException(Exception ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );


        return new ResponseEntity<>(cer, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        //  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        return new ResponseEntity<>(cer , HttpStatus.NOT_FOUND);


    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(cer, HttpStatus.BAD_REQUEST);
    }
}
