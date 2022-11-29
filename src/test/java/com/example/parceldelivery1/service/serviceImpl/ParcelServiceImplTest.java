package com.example.parceldelivery1.service.serviceImpl;

import com.example.parceldelivery1.dto.Billing;
import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.model.Parcel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ParcelServiceImpl.class)
class ParcelServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcelServiceImpl parcelServiceMock;

    @Test
    void placeOrder() {
        Parcel parcelMock = new Parcel();
        parcelMock.setWeight(4);
        parcelMock.setHeight(20);
        parcelMock.setWidth(20);
        parcelMock.setLength(10);

        Billing billingMock = new Billing();
        billingMock.setSize(Size.LARGE);
        billingMock.setCost(195.0);

        FinalParcel finalParcelMock = new FinalParcel(parcelMock,billingMock);

        String voucher = "sfds";

        Mockito.when(parcelServiceMock.placeOrder(parcelMock,voucher)).thenReturn(finalParcelMock);
        assertThat(parcelServiceMock.placeOrder(parcelMock,voucher)).isEqualTo(finalParcelMock);
    }
}