package com.example.parceldelivery1.service.serviceImpl;


import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.helper.DataCalculator;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private Logger logger;

    @Autowired
    private DataCalculator dataCalculator;


    /**
     * This is a service layer method which will use helper method to provide the output .
     * This is a method which is responsible for providing order details and final cost.
     * @param Parcel parcel,String voucher
     * @return FinalParcel
     */
    @Override
    public FinalParcel placeOrder(Parcel parcel,String voucher) {
        logger.info("Calculation for your order is initiated");
        return new FinalParcel(parcel, dataCalculator.getFinalCost(parcel,voucher));
    }
}
