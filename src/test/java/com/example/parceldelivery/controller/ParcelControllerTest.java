package com.example.parceldelivery.controller;

import com.example.parceldelivery.dto.ExceptionDTO;
import com.example.parceldelivery.model.Parcel;
import com.example.parceldelivery.service.ParcelService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tngtech.keycloakmock.api.KeycloakMock;
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

//import static com.tngtech.keycloakmock.api.ServerConfig.aServerConfig;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ParcelController.class)
class ParcelControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcelService parcelService;


    @Test
    void getOrder() throws Exception {

        Parcel parcelMock = new Parcel(4D,20D,20D,10D);


        double amountMock = 175.5;

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

        Parcel parcelMock = new Parcel(90D,20D,20D,10D);


        double amountMock = 175.5;

        String URI = "http://localhost:9082/parcel/getCost?voucher=aaa";

        Mockito.when(parcelService.getCost(Mockito.any(),Mockito.anyString())).thenReturn(amountMock);

        ExceptionDTO exceptionDTO = new ExceptionDTO(400,"[Heavy should be less than 50]");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(parcelMock))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(outputInJson).isEqualTo(new ObjectMapper().writeValueAsString(exceptionDTO));

    }
}