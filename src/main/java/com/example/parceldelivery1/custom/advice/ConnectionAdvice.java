package com.example.parceldelivery1.custom.advice;

import com.example.parceldelivery1.dto.ExceptionDTO;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice(value = "Connection Exception Advice")
@Slf4j
public class ConnectionAdvice {

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> handleConstraintInput(ConnectException connectException) {
        String fieldErrors = connectException.getMessage();
        log.error("Exception while connection to server");
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), fieldErrors), HttpStatus.BAD_REQUEST);

    }
}
