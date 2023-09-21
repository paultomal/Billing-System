package com.paul.billing_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationIsNotGivenException.class)
    public ResponseEntity<?> customAuthenticationIsNotGivenException(AuthenticationIsNotGivenException exception){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails( exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public ResponseEntity<String> handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) {
        String errorMessage = "Unsupported Media Type: " + ex.getContentType();
        return new ResponseEntity<>(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
