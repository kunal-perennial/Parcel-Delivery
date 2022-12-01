package com.example.parceldelivery1.helper;

import com.example.parceldelivery1.dto.Amount;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.model.Parcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * This class is a helper class which contain helper function which is used by the service layer for the calculation purpose.
 *
 * @author Kunal Duse <kunal.duse@perennialsys.com>
 */
@Controller
@Slf4j
public class DataCalculator {

    @Value("${minimum.weight}")
    private double minWeight;
    @Value("${maximum.weight}")
    private double maxWeight;
    @Value("${maximum.volume.forSmall}")
    private double maxSmallVolume;
    @Value("${maximum.volume.forMedium}")
    private double maxMediumVolume;
    @Value("${cost.weight}")
    private double costPerWeight;
    @Value("${cost.volume.small}")
    private double costSmallVolume;
    @Value("${cost.volume.Medium}")
    private double costMediumVolume;
    @Value("${cost.volume.Large}")
    private double costLargeVolume;


    /**
     * This is a method which is responsible for finding out the volume of the Parcel based on the details of parcel
     * provided by the user.
     *
     * @param parcel
     * @return int
     */
    public double calculateVolume(Parcel parcel) {
        double volume = parcel.getHeight() * parcel.getLength() * parcel.getWidth();
        return volume;
    }

    /**
     * This is a method which is responsible for finding out the cost as per the set of rules.
     * The cost will be calculated on the basis of weight or volume.
     *
     * @param parcel
     * @return Amount
     */
    public Amount calculateCost(Parcel parcel) {
        log.info("calculating Cost for parcel");
        double cost;
        if (minWeight < parcel.getWeight() && parcel.getWeight() < maxWeight) {
            cost = Math.round(parcel.getWeight() * costPerWeight);
            return new Amount(Size.HEAVY, cost);
        } else if (calculateVolume(parcel) < maxSmallVolume) {
            cost = Math.round(calculateVolume(parcel) * costSmallVolume);
            return new Amount(Size.SMALL, cost);
        } else if (maxSmallVolume < calculateVolume(parcel) && calculateVolume(parcel) < maxMediumVolume) {
            cost = Math.round(calculateVolume(parcel) * costMediumVolume);
            return new Amount(Size.MEDIUM, cost);
        } else {
            cost = Math.round(calculateVolume(parcel) * costLargeVolume);
            return new Amount(Size.LARGE, cost);
        }

    }

}
