package com.example.parceldelivery1.service.serviceImpl;

import com.example.parceldelivery1.dto.VoucherDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {


    @Mock
    private WebClient.Builder webClientBuilder;

    @Spy
    private VoucherServiceImpl voucherService;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(voucherService,"url","https://mynt-exam.mocklab.io/voucher/");
    }

    @Test
    void getDiscount() throws ParseException {
          String date = "2020-09-16";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        VoucherDTO voucherDTO = new VoucherDTO("MYNT",12.25, format.parse(date));
        String voucher = "MYNT";

        when(Mono.just(webClientBuilder.build().get().uri("https://mynt-exam.mocklab.io/voucher/MYNT?key=apikey").retrieve().bodyToMono(VoucherDTO.class).block())).thenReturn(voucherDTO);
        when(Mono.just(voucherService.getDiscount(voucher))).thenReturn(12.25);
        assertEquals(12.25,voucherService.getDiscount(voucher));
    }
}