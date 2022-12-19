package com.example.parceldelivery.helper;

import com.example.parceldelivery.enums.Size;
import com.example.parceldelivery.model.Parcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class is a helper class which contain helper function which is used by the service layer for the calculation purpose.
 *
 * @author Kunal Duse <kunal.duse@perennialsys.com>
 */
@Component
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

    @Autowired
    private VolumeCalculator volumeCalculator;


    /**
     * This is a method which is responsible for finding out the cost as per the set of rules.
     * The cost will be calculated on the basis of weight or volume.
     *
     * @param parcel
     * @return Amount
     */
    public double calculateCost(Parcel parcel) {
        log.info("calculating Cost for parcel");

            Map<Size, Rules<Double>> rulesMap = createRules(parcel);
            return Stream.of(Size.values())
                    .filter(size -> rulesMap.get(size).condition.get())
                    .map(size -> rulesMap.get(size).process.get())
                    .findFirst()
                    .orElseGet(() -> 0D);
    }
    Map<Size,Rules<Double>> createRules(Parcel parcel){
        Map<Size, Rules<Double>> rules = new HashMap<>();
        rules.put(Size.HEAVY,createRuleForHeavy(parcel));
        rules.put(Size.SMALL,createRuleForSmall(parcel));
        rules.put(Size.MEDIUM,createRuleForMedium(parcel));
        rules.put(Size.LARGE,createRuleForLarge(parcel));
        return rules;
    }
        Rules<Double> createRuleForHeavy (Parcel parcel){
            return createRule(
                    () -> (minWeight < parcel.getWeight() && parcel.getWeight() <= maxWeight),
                    () -> (double) Math.round(parcel.getWeight() * costPerWeight)
            );
        }

        Rules<Double> createRuleForSmall (Parcel parcel){
            return createRule(
                    () -> (volumeCalculator.calculateVolume(parcel) < maxSmallVolume),
                    () -> (double) Math.round(volumeCalculator.calculateVolume(parcel) * costSmallVolume)
            );
        }

        Rules<Double> createRuleForMedium (Parcel parcel){
            return createRule(
                    () -> (maxSmallVolume < volumeCalculator.calculateVolume(parcel) && volumeCalculator.calculateVolume(parcel) < maxMediumVolume),
                    () -> (double) Math.round(volumeCalculator.calculateVolume(parcel) * costMediumVolume)
            );
        }

        Rules<Double> createRuleForLarge (Parcel parcel){
            return createRule(
                    () -> (maxMediumVolume < volumeCalculator.calculateVolume(parcel)),
                    () -> (double) (Math.round(volumeCalculator.calculateVolume(parcel) * costLargeVolume))
            );
        }
        Rules<Double> createRule (Supplier < Boolean > condition, Supplier < Double > process){
            return new Rules<>(condition, process);
        }


}

