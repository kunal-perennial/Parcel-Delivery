package com.example.parceldelivery.custom.exceptions;

public class TryAgainException extends RuntimeException {

    public TryAgainException(String message) {
        super(message);
    }
}
