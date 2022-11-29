package com.example.parceldelivery1.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Class is used to store the information related to parcel which is entered by users.
 * @author Kunal Duse <kunal.duse@perennialsys.com>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {

    /**
     * This is a mandatory parameter which is used to store weight of the parcel in KG.
     * This parameter should not be zero or negative.
     */
    private int weight;

    /**
     * This is a mandatory parameter which is used to store height of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    private int height;

    /**
     * This is a mandatory parameter which is used to store width of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    private int width;

    /**
     * This is a mandatory parameter which is used to store length of the parcel in cm.
     * This parameter should not be zero or negative.
     */
    private int length;

}