package com.example.parceldelivery.service;


import com.example.parceldelivery.model.Parcel;

public interface ParcelService {
    double getCost(Parcel parcel, String voucher);

}
