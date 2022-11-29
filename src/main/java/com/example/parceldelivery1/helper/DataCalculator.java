package com.example.parceldelivery1.helper;

import com.example.parceldelivery1.dto.Billing;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.model.Parcel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * This class is a helper class which contain helper function which is used by the service layer for the calculation purpose.
 * @author Kunal Duse <kunal.duse@perennialsys.com>
 */
@Controller
public class DataCalculator {

    @Autowired
    private Logger logger;
    @Value("${spring.minimum.weight}")
    private int minWeight;
    @Value("${spring.maximum.weight}")
    private int maxWeight;
    @Value("${spring.maximum.volume.forSmall}")
    private int maxSmallVolume;
    @Value("${spring.maximum.volume.forMedium}")
    private int maxMediumVolume;
    @Value("${spring.cost.weight}")
    private float costPerWeight;
    @Value("${spring.cost.volume.small}")
    private float costSmallVolume;
    @Value("${spring.cost.volume.Medium}")
    private float costMediumVolume;
    @Value("${spring.cost.volume.Large}")
    private float costLargeVolume;


    /**
     * This is a method which is responsible for finding out the volume of the Parcel based on the details of parcel
     * provided by the user.
     * @param Parcel parcel
     * @return int
     */
        private int calculateVolume(Parcel parcel) {
            int volume = parcel.getHeight() * parcel.getLength() * parcel.getWidth();
            logger.info("Volume of the order is: "+volume);
            return volume;
        }

    /**
     * This is a method which is responsible for finding out the cost as per the set of rules.
     * The cost will be calculated on the basis of weight or volume.
     * @param Parcel parcel
     * @return Billing
     */
    private Billing calculateCost(Parcel parcel) {
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

    /**
     * This is a method which is responsible for providing discount as per the voucher code provided by User.
     * If voucher code is not provided by user then it will return Zero.
     * @param String voucher
     * @return int
     */
        private int getDiscount(String voucher) {
            if (!voucher.isBlank()) {
                logger.info("applied discount successfully: ");
                return 5;
            }
            return 0;
        }

    /**
     * This is a method which is responsible for calculating final cost on the basis of weight or volume.
     * If voucher code is provided by user then it will give final cost with discount.
     * @param Parcel parcel,String voucher
     * @return Billing
     */
        public Billing getFinalCost(Parcel parcel,String voucher){
            Billing billing = calculateCost(parcel);
            double finalCost = billing.getCost() - getDiscount(voucher);
            if(finalCost <= 0){
                billing.setCost(0);
                return billing;
            }
            billing.setCost(finalCost);
            logger.info("Billing is done !!");
            return billing;
        }

}
