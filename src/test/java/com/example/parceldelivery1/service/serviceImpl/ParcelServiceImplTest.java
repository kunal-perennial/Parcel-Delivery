package com.example.parceldelivery1.service.serviceImpl;

import com.example.parceldelivery1.dto.Billing;
import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.dto.VoucherDTO;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.helper.DataCalculator;
import com.example.parceldelivery1.model.Parcel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParcelServiceImplTest {

    @Mock
    private DataCalculator dataCalculator;

    @Mock
    private WebClient.Builder webClientBuilder;
    @InjectMocks
    private ParcelServiceImpl parcelServiceMock;

    @Test
    void placeOrder() {
        Parcel parcelMock = new Parcel(9,20,20,10);

        Billing billingMock = new Billing(Size.LARGE,200.0);

        FinalParcel finalParcelMock = new FinalParcel(parcelMock,billingMock);
        VoucherDTO voucherDTOMock = new VoucherDTO("aaa",10);

        String voucher = "aaa";

        when(dataCalculator.calculateCost(parcelMock)).thenReturn(billingMock);
        when(webClientBuilder.build().get().uri("http://localhost:9083/voucher/getVoucher/ccc").retrieve().bodyToMono(VoucherDTO.class).block()).thenReturn(voucherDTOMock);
        assertEquals(finalParcelMock,parcelServiceMock.placeOrder(parcelMock,voucher));
    }
}