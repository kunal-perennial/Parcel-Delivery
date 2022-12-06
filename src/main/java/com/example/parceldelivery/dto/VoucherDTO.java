package com.example.parceldelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {
    private String code;
    private double discount;
    private Date expiry;
}
