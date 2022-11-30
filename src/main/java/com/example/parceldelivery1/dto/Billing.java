package com.example.parceldelivery1.dto;


import com.example.parceldelivery1.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is used to store the parcel size and final cost.Data is store here from the service layer.
 *
 * @author Kunal Duse <kunal.duse@perennialsys.com>
 */
@Data
@AllArgsConstructor
public class Billing {
    private Size size;
    private double cost;
}
