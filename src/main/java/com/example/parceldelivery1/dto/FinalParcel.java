package com.example.parceldelivery1.dto;



import com.example.parceldelivery1.model.Parcel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is used to store the parcel data and billing data together.Data is store here from the service layer.
 * @author Kunal Duse <kunal.duse@perennialsys.com>
 */
@Data
@AllArgsConstructor
public class FinalParcel {
    private Parcel parcel;
    private Billing billing;
}
