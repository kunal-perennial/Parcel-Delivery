package com.example.parceldelivery1.helper;

import com.example.parceldelivery1.dto.Billing;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.model.Parcel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DataCalculatorTest {


    @Spy
    private DataCalculator dataCalculatorMock;

    @Test
    void calculateVolume() {
        Parcel parcelMock = new Parcel(4,20,20,10);
        int volume = 20*20*10;
        when(dataCalculatorMock.calculateVolume(parcelMock)).thenReturn(volume);
        assertEquals(4000,dataCalculatorMock.calculateVolume(parcelMock));
    }

    @Test
    void calculateCost() {
        Parcel parcelMock = new Parcel(4,20,20,10);
        Billing billingMock = new Billing(Size.LARGE,195.0);
        when(dataCalculatorMock.calculateCost(parcelMock)).thenReturn(billingMock);
        assertEquals(billingMock,dataCalculatorMock.calculateCost(parcelMock));
    }

}