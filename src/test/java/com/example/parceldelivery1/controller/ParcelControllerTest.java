package com.example.parceldelivery1.controller;

import com.example.parceldelivery1.dto.Amount;
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
@WebMvcTest(value = ParcelController.class)
class ParcelControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcelService parcelService;

    @MockBean
    private Logger logger;


    @Test
    void getOrder() throws Exception {

        Parcel parcelMock = new Parcel(4,20,20,10);


        Amount amountMock = new Amount(Size.LARGE,195.0);

        String voucher = "aaa";
        String URI = "http://localhost:9082/parcel/getCost?voucher=aaa";

        Mockito.when(parcelService.getCost(Mockito.any(),Mockito.anyString())).thenReturn(amountMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(parcelMock))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(outputInJson).isEqualTo(new ObjectMapper().writeValueAsString(amountMock));

    }



    @Test
    void getOrderNegativeScenario() throws Exception {

        Parcel parcelMock = new Parcel(90,-20,0,10);


        Amount amountMock = new Amount(Size.LARGE,195.0);

        int errorCode = 400;
        String URI = "http://localhost:9082/parcel/getCost?voucher=aaa";

        Mockito.when(parcelService.getCost(Mockito.any(),Mockito.anyString())).thenReturn(amountMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(parcelMock))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        int outputInJson = result.getResponse().getStatus();

        assertThat(outputInJson).isEqualTo(errorCode);


    }
}