package com.example.parceldelivery1.custom.advice;

import com.example.parceldelivery1.dto.InvalidInputs;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class Advice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintInput(ConstraintViolationException invalidConstraint) {
        String fieldErrors = invalidConstraint.getMessage();
        List<String> errorList = new ArrayList<>();
        errorList.add(fieldErrors);
        return new ResponseEntity<>(new InvalidInputs(HttpStatus.BAD_REQUEST.value(), errorList.toString()), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleConstraintInput(MissingServletRequestParameterException missingParameter) {
        String fieldErrors = missingParameter.getMessage();
        List<String> errorList = new ArrayList<>();
        errorList.add(fieldErrors);
        return new ResponseEntity<>(new InvalidInputs(HttpStatus.BAD_REQUEST.value(), errorList.toString()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleConstraintInput(ConnectException connectException) {
        String fieldErrors = connectException.getMessage();
        return new ResponseEntity<>(new InvalidInputs(HttpStatus.BAD_REQUEST.value(), fieldErrors), HttpStatus.BAD_REQUEST);

    }

}
