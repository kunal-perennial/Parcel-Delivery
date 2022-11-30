package com.example.parceldelivery1.constants;

public final class ControllerMessage {
    public static final String POSITIVE_WEIGHT_MESSAGE = "Weight should not be zero or negative, and should be greater than 50";
    public static final String MAXIMUM_WEIGHT_MESSAGE = "Weight should be less than 50";
    public static final String POSITIVE_HEIGHT_MESSAGE = "height should not be zero or negative";
    public static final String NOTNULL_HEIGHT_MESSAGE = "height should not be zero";
    public static final String POSITIVE_WIDTH_MESSAGE = "Width should not be zero or negative";
    public static final String NOTNULL_WIDTH_MESSAGE = "Width should not be zero";
    public static final String POSITIVE_LENGTH_MESSAGE = "Length should not be zero or negative";
    public static final String NOTNULL_LENGTH_MESSAGE = "Length should not be zero";
    private ControllerMessage() {
    }

}
