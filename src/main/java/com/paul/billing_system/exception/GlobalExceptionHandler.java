package com.paul.billing_system.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public ResponseEntity<String> handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) {
        String errorMessage = "Unsupported Media Type: " + ex.getContentType();
        return new ResponseEntity<>(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customMethodArgumentNotValidException(MethodArgumentNotValidException m) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Input Validation Is in Correct!!", m.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> customConstraintViolationException(ConstraintViolationException ex) {
        List<String> errorMessages = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation error", errorMessages.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<?> customEmailAlreadyTakenException(EmailAlreadyTakenException e) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Email Already Taken", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameAlreadyTakenException.class)
    public ResponseEntity<?> customUserNameAlreadyTakenException(UserNameAlreadyTakenException u) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Username is Already Taken", u.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsNotFoundException.class)
    public ResponseEntity<?> customUserIsNotFoundException(UserIsNotFoundException un) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "User is Not Found", un.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpecialtyNameAlreadyTakenException.class)
    public ResponseEntity<?> customSpecialtyNameAlreadyTakenException(SpecialtyNameAlreadyTakenException s) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "This Specialty is Already in the Database!!", s.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestigationServiceNameAlreadyTaken.class)
    public ResponseEntity<?> customInvestigationServiceNameAlreadyTaken(InvestigationServiceNameAlreadyTaken i) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "This Investigation is Already in Database", i.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrgCodeIsAlreadyTakenException.class)
    public ResponseEntity<?> customOrgCodeIsAlreadyTakenException(OrgCodeIsAlreadyTakenException o) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "This OrgCode Is Already Has been Used!!!!! Try Something Else.", o.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
