package com.example.parceldelivery.helper;

import com.example.parceldelivery.model.Parcel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VolumeCalculatorTest {

   @InjectMocks
    private VolumeCalculator volumeCalculator;


    @Test
    void calculateVolume() {
        Parcel parcelMock = new Parcel(4D,20D,10D,5D);
        assertEquals(1000D,volumeCalculator.calculateVolume(parcelMock));

    }
}