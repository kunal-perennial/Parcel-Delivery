package com.example.parceldelivery1.helper;

import com.example.parceldelivery1.dto.Billing;
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
    private int minWeight;
    @Value("${maximum.weight}")
    private int maxWeight;
    @Value("${maximum.volume.forSmall}")
    private int maxSmallVolume;
    @Value("${maximum.volume.forMedium}")
    private int maxMediumVolume;
    @Value("${cost.weight}")
    private float costPerWeight;
    @Value("${cost.volume.small}")
    private float costSmallVolume;
    @Value("${cost.volume.Medium}")
    private float costMediumVolume;
    @Value("${cost.volume.Large}")
    private float costLargeVolume;


    /**
     * This is a method which is responsible for finding out the volume of the Parcel based on the details of parcel
     * provided by the user.
     *
     * @param Parcel parcel
     * @return int
     */
    public int calculateVolume(Parcel parcel) {
        int volume = parcel.getHeight() * parcel.getLength() * parcel.getWidth();
        return volume;
    }

    /**
     * This is a method which is responsible for finding out the cost as per the set of rules.
     * The cost will be calculated on the basis of weight or volume.
     *
     * @param Parcel parcel
     * @return Billing
     */
    public Billing calculateCost(Parcel parcel) {
        log.info("calculating Cost for parcel");
        double cost;
        if (minWeight < parcel.getWeight() && parcel.getWeight() < maxWeight) {
            cost = Math.round(parcel.getWeight() * costPerWeight);
            return new Billing(Size.HEAVY, cost);
        } else if (calculateVolume(parcel) < maxSmallVolume) {
            cost = Math.round(calculateVolume(parcel) * costSmallVolume);
            return new Billing(Size.SMALL, cost);
        } else if (maxSmallVolume < calculateVolume(parcel) && calculateVolume(parcel) < maxMediumVolume) {
            cost = Math.round(calculateVolume(parcel) * costMediumVolume);
            return new Billing(Size.MEDIUM, cost);
        } else {
            cost = Math.round(calculateVolume(parcel) * costLargeVolume);
            return new Billing(Size.LARGE, cost);
        }

    }

}
