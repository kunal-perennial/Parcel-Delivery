package com.example.parceldelivery1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {
    private String code;
    private double discount;
    private Date expiry;
}
