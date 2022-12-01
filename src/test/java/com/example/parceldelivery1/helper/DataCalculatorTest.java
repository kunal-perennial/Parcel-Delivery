package com.example.parceldelivery1.helper;

import com.example.parceldelivery1.dto.Amount;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.model.Parcel;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class DataCalculatorTest {

    @Spy
    private DataCalculator dataCalculator;
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
    void calculateVolume() {
        Parcel parcelMock = new Parcel(4,20,20,10);
        assertEquals(4000,dataCalculator.calculateVolume(parcelMock));
    }

    @Test
    void calculateCost() {
        Parcel parcelMock = new Parcel(4,20,20,10);
        Amount amountMock = new Amount(Size.LARGE,200.0);
        assertEquals(amountMock.getCost(),dataCalculator.calculateCost(parcelMock).getCost());
    }
}