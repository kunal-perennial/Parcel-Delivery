package com.example.parceldelivery1.custom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class InvalidInputs {

    private int errorCode;
    private String errorMessage;
}