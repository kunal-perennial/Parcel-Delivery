package com.example.parceldelivery1.service.serviceImpl;

import com.example.parceldelivery1.dto.Amount;
import com.example.parceldelivery1.dto.VoucherDTO;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.helper.DataCalculator;
import com.example.parceldelivery1.model.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParcelServiceImplTest {


    @Mock
    private DataCalculator dataCalculator;

    @Mock
    private VoucherServiceImpl voucherService;

    @Spy
    private ParcelServiceImpl parcelService;

    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(dataCalculator,"minWeight",10D);
        ReflectionTestUtils.setField(dataCalculator,"maxWeight",50D);
        ReflectionTestUtils.setField(dataCalculator,"maxSmallVolume",1500D);
        ReflectionTestUtils.setField(dataCalculator,"maxMediumVolume",2500D);
        ReflectionTestUtils.setField(dataCalculator,"costPerWeight",20D);
        ReflectionTestUtils.setField(dataCalculator,"costSmallVolume",0.03D);
        ReflectionTestUtils.setField(dataCalculator,"costMediumVolume",0.04D);
        ReflectionTestUtils.setField(dataCalculator,"costLargeVolume",0.05D);
    }
    @Test
    void placeOrder() {
        Parcel parcelMock = new Parcel(9,20,20,10);
        Amount amountMock = new Amount(Size.LARGE,200.0);
        String voucher = "MYNT";

        when(dataCalculator.calculateVolume(parcelMock)).thenReturn(4000D);
        when(dataCalculator.calculateCost(parcelMock)).thenReturn(amountMock);
        when(parcelService.getCost(parcelMock,voucher)).thenReturn(amountMock);
        assertEquals(amountMock,parcelService.getCost(parcelMock,voucher));
    }
}