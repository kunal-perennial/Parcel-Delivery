package com.example.parceldelivery1.controller;

import com.example.parceldelivery1.dto.Billing;
import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.enums.Size;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcelService parcelService;

    @MockBean
    private Logger logger;


    @Test
    void getOrder() throws Exception {

        Parcel parcelMock = new Parcel(4,20,20,10);

        Billing billingMock = new Billing(Size.LARGE,195.0);

        FinalParcel finalParcel = new FinalParcel(parcelMock,billingMock);

        String voucher = "sfds";
        String URI = "http://localhost:9082/parcel/order?weight=4&height=20&width=20&length=10&voucher=sfds";

        Mockito.when(parcelService.placeOrder(Mockito.any(),Mockito.anyString())).thenReturn(finalParcel);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(outputInJson).isEqualTo(new ObjectMapper().writeValueAsString(finalParcel));

    }
}