package com.example.parceldelivery.helper;


import com.example.parceldelivery.model.Parcel;
import org.springframework.stereotype.Component;

@Component
public class VolumeCalculator {

    /**
     * This is a method which is responsible for finding out the volume of the Parcel based on the details of parcel
     * provided by the user.
     *
     * @param parcel
     * @return int
     */
    public double calculateVolume(Parcel parcel) {
        return parcel.getHeight() * parcel.getLength() * parcel.getWidth();
    }
}
