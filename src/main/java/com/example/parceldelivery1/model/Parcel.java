package com.example.parceldelivery1.model;


import com.example.parceldelivery1.constants.ControllerMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * This Class is used to store the information related to parcel which is entered by users.
 *
 * @author Kunal Duse <kunal.duse@perennialsys.com>
 */
@Data
@AllArgsConstructor
public class Parcel {

    /**
     * This is a mandatory parameter which is used to store weight of the parcel in KG.
     * This parameter should not be zero or negative.
     */
    @Positive(message = ControllerMessage.POSITIVE_WEIGHT_MESSAGE)
    @Max(value = 50, message = ControllerMessage.MAXIMUM_WEIGHT_MESSAGE)
    private float weight;

    /**
     * This is a mandatory parameter which is used to store height of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    @Positive(message = ControllerMessage.POSITIVE_HEIGHT_MESSAGE)
    @NotNull(message = ControllerMessage.NOTNULL_HEIGHT_MESSAGE)
    private float height;

    /**
     * This is a mandatory parameter which is used to store width of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    @Positive(message = ControllerMessage.POSITIVE_WIDTH_MESSAGE)
    @NotNull(message = ControllerMessage.NOTNULL_WIDTH_MESSAGE)
    private float width;

    /**
     * This is a mandatory parameter which is used to store length of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    @Positive(message = ControllerMessage.POSITIVE_LENGTH_MESSAGE)
    @NotNull(message = ControllerMessage.NOTNULL_LENGTH_MESSAGE)
    private float length;

}