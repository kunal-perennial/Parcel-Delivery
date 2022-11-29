package com.example.parceldelivery1.controller;

import com.example.parceldelivery1.dto.Billing;
import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcelService parcelService;

    @Test
    void getOrder() throws Exception {

        Parcel parcelMock = new Parcel();
        parcelMock.setWeight(4);
        parcelMock.setHeight(20);
        parcelMock.setWidth(20);
        parcelMock.setLength(10);

        Billing billingMock = new Billing();
        billingMock.setSize(Size.LARGE);
        billingMock.setCost(195.0);

        FinalParcel finalParcel = new FinalParcel(parcelMock,billingMock);

        String voucher = "sfds";
        String URI = "http://localhost:9082/parcel/order?weight=4&height=20&width=20&length=10&voucher=sfds";

        Mockito.when(parcelService.placeOrder(Mockito.any(),Mockito.anyString())).thenReturn(finalParcel);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = finalParcel.toString();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(outputInJson).isEqualTo(expectedJson);

    }
}