package com.example.parceldelivery1.custom.advice;

import com.example.parceldelivery1.dto.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice(value = "validation Exceptions Advice")
@Slf4j
public class ControllerInputAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleConstraintInput(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getFieldErrors();
        List<String> errorList = new ArrayList<>();
        for (FieldError field: fieldErrors) {
            errorList.add(field.getDefaultMessage());
        }
        log.error("Invalid input Exception");
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), errorList.toString()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleConstraintInput(HttpMessageNotReadableException httpMessageNotReadableException) {
        String fieldErrors = httpMessageNotReadableException.getMessage();
        log.error("Invalid data in request body of POST method");
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), fieldErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleConstraintInput(MissingServletRequestParameterException missingParameter) {
        String fieldErrors = missingParameter.getMessage();
        List<String> errorList = new ArrayList<>();
        errorList.add(fieldErrors);
        log.error("Missing data in request body of method");
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), errorList.toString()), HttpStatus.BAD_REQUEST);
    }
}
