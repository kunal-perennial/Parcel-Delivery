package com.example.parceldelivery.model;



import com.example.parceldelivery.constants.ControllerMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

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
    private Double weight;

    /**
     * This is a mandatory parameter which is used to store height of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    @Positive(message = ControllerMessage.POSITIVE_HEIGHT_MESSAGE)
    private double height;

    /**
     * This is a mandatory parameter which is used to store width of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    @Positive(message = ControllerMessage.POSITIVE_WIDTH_MESSAGE)
    private double width;

    /**
     * This is a mandatory parameter which is used to store length of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    @Positive(message = ControllerMessage.POSITIVE_LENGTH_MESSAGE)
    private double length;

}