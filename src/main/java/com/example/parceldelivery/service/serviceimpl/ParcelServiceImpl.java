package com.example.parceldelivery.service.serviceimpl;


import com.example.parceldelivery.helper.DataCalculator;
import com.example.parceldelivery.model.Parcel;
import com.example.parceldelivery.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private DataCalculator dataCalculator;
    @Autowired
    private VoucherServiceImpl voucherService;


    /**
     * This is a method which is responsible for calculating final cost on the basis of weight or volume.
     * If voucher code is provided by user then it will give final cost with discount.
     *
     * @param parcel
     * @param voucher
     * @return Amount
     */
    public double getCost(Parcel parcel, String voucher) {
        double amount = dataCalculator.calculateCost(parcel);
        double discountedAmount = voucherService.getDiscount(voucher)/100;
        double payingAmount = amount - (amount * discountedAmount);
        log.info("Amount is calculated !!");
        return payingAmount;
    }
}
