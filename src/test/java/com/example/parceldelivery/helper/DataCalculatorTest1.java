package com.example.parceldelivery.helper;


import com.example.parceldelivery.model.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DataCalculatorTest1 {

//    @Mock
//    private Heavy heavy;
//    @Mock
//    private Small small;
//    @Mock
//    private Medium medium;
//    @Mock
//    private Large large;

    @Mock
    private VolumeCalculator volumeCalculator;

    @InjectMocks
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
    void calculateCostForWeight() {
        Parcel parcelMock = new Parcel(11D,20D,20D,10D);
//        when(heavy.isApplicable(parcelMock)).thenReturn(true);
//        when(heavy.calculateCost(parcelMock)).thenReturn(220D);
        //assertEquals(true,weight.isApplicable(parcelMock));
        assertEquals(220D,dataCalculator.calculateCost(parcelMock));
    }

    @Test
    void calculateCostForSmall() {
        Parcel parcelMock = new Parcel(4D,20D,10D,5D);
        when(volumeCalculator.calculateVolume(parcelMock)).thenReturn(1000D);
        assertEquals(30D,dataCalculator.calculateCost(parcelMock));
    }

    @Test
    void calculateCostForMedium() {
        Parcel parcelMock = new Parcel(4D,10D,20D,10D);
//        when(medium.isApplicable(parcelMock)).thenReturn(true);
//        when(medium.calculateCost(parcelMock)).thenReturn(220D);
        when(volumeCalculator.calculateVolume(parcelMock)).thenReturn(2000D);
        assertEquals(80D,dataCalculator.calculateCost(parcelMock));
    }

    @Test
    void calculateCostForLarge() {
        Parcel parcelMock = new Parcel(4D,20D,20D,20D);
//        when(large.calculateCost(parcelMock)).thenReturn(220D);
        when(volumeCalculator.calculateVolume(parcelMock)).thenReturn(8000D);
        assertEquals(400D,dataCalculator.calculateCost(parcelMock));
    }


//    @Test
//    void calculateCostAll() {
//        Parcel parcelMock = new Parcel(11D,20D,20D,10D);
//        //when(heavy.calculateCost(parcelMock)).thenReturn(220D);
//        //assertEquals(true,weight.isApplicable(parcelMock));
//        assertEquals(220D,dataCalculator.calculateCost(parcelMock));
//    }
}