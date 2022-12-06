package com.example.parceldelivery.helper;

import com.example.parceldelivery.model.Parcel;

public interface Rule {

    boolean isApplicable(Parcel parcel);
    double calculateCost(Parcel parcel);
}
