package com.example.parceldelivery1.service.serviceImpl;


import com.example.parceldelivery1.dto.Amount;
import com.example.parceldelivery1.dto.VoucherDTO;
import com.example.parceldelivery1.helper.DataCalculator;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
    public Amount getCost(Parcel parcel, String voucher) {
        Amount amount = dataCalculator.calculateCost(parcel);
        double payingAmount = amount.getCost() - (amount.getCost()* voucherService.getDiscount(voucher)/100);
        if (payingAmount <= 0) {
            amount.setCost(0);
            return amount;
        }
        amount.setCost(payingAmount);
        log.info("Amount is calculated !!");
        return amount;
    }
}
