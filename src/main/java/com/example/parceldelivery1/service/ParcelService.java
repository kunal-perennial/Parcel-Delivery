package com.example.parceldelivery1.service;


import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.model.Parcel;

public interface ParcelService {
    FinalParcel placeOrder(Parcel parcel,String voucher);

}
