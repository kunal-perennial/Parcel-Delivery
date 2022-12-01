package com.example.parceldelivery1.service;


import com.example.parceldelivery1.dto.Amount;
import com.example.parceldelivery1.model.Parcel;

public interface ParcelService {
    Amount getCost(Parcel parcel, String voucher);

}
