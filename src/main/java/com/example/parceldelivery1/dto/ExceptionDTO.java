package com.example.parceldelivery1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ExceptionDTO {

    private int errorCode;
    private String errorMessage;
}