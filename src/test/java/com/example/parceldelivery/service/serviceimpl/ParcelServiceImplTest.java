package com.example.parceldelivery.service.serviceimpl;

import com.example.parceldelivery.helper.DataCalculator;
import com.example.parceldelivery.model.Parcel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParcelServiceImplTest {

    @Mock
    private DataCalculator dataCalculator;

    @Mock
    private VoucherServiceImpl voucherService;

    @InjectMocks
    private ParcelServiceImpl parcelService;

    @Test
    void testWithNoDiscount() {
        Parcel parcelMock = new Parcel(9D,20D,20D,10D);
        when(dataCalculator.calculateCost(parcelMock)).thenReturn(200D);
        when(voucherService.getDiscount(anyString())).thenReturn(0D);
        assertEquals(200D,parcelService.getCost(parcelMock,""));
    }

    @Test
    void testWithDiscount() {
        Parcel parcelMock = new Parcel(9D,20D,20D,10D);
        when(dataCalculator.calculateCost(parcelMock)).thenReturn(200D);
        when(voucherService.getDiscount(anyString())).thenReturn(12.25D);
        assertEquals(175.5D,parcelService.getCost(parcelMock,"MYNT"));
    }
}