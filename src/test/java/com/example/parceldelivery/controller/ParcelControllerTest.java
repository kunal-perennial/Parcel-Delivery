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
                //.header("Authorization","eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI0dEhsbDMxQ2NVMEJMeUd3emhNZUEwSnUwcm41WTR2YUZ2c190U2plNlVBIn0.eyJleHAiOjE2NzAzMjQ4OTEsImlhdCI6MTY3MDMyNDU5MSwianRpIjoiNmQ2NTZlMGQtZTljZi00N2RhLThkOTgtZTYwNmYyNjUyN2Q4IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL3BhcmNlbCIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIxOWM0MmM1NC0xNmNmLTQzMzQtYWEyMy1jODZkYzk2OGVhZjYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJwYXJjZWwta2V5Y2xvYWsiLCJzZXNzaW9uX3N0YXRlIjoiMmE3YTJjZjItNjIxOC00ZGY5LWE3NmItMDY3MzRmMDRlMDgzIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjkwODIiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIiwiZGVmYXVsdC1yb2xlcy1wYXJjZWwiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiMmE3YTJjZjItNjIxOC00ZGY5LWE3NmItMDY3MzRmMDRlMDgzIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJrdW5hbGR1c2UifQ.TueQmNQFSUysPIvAnsJEJ7_BGuXGSeAYZi-r2F4LTBvZ-esFIHy_EIZX8YUXJ1FM3yw1CfWkJGRmRrGLx6qhNO2GAm20eLioSPJybUC6OEiQsPs01e8fjZPNGzsaA6c2_6o8p7oWaSq8QMI5y6jRJrTLlzCelwzYtHlSHIS1vqqNn9GIrnpqvGyRwwgttyUQtaDA5azb1kzEupD9ayj6d3mMB6rILcJXxk77o1gNgX2IE9cGft8EibWFVXhhuyhFzeRCh3FNLQIjSKhLbI5CsBDEfLHNnS788Np6kXrAacHivRGcyx35f96RJxBouDI_E2jmUbx69c_UpBo2rz3Wfg")
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