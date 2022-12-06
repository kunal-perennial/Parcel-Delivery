package com.example.parceldelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ExceptionDTO {

    private int errorCode;
    private String errorMessage;
}