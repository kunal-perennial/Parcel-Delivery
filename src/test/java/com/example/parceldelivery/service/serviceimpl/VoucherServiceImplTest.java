package com.example.parceldelivery.service.serviceimpl;

import com.example.parceldelivery.custom.exceptions.TryAgainException;
import com.example.parceldelivery.dto.VoucherDTO;
import io.netty.resolver.dns.DnsNameResolverTimeoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {

    @Mock
    private WebClient webClientMock;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Mock
    private Mono<VoucherDTO> voucherMono;

    @InjectMocks
    private VoucherServiceImpl voucherService;

    @BeforeEach
    void init(){
        ReflectionTestUtils.setField(voucherService,"url","https://mynt-exam.mocklab.io/voucher/");
    }

    @Test
    void getDiscount() throws ParseException {
        String date = "2022-12-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        VoucherDTO voucherDTO = new VoucherDTO("MYNT", 12.25D, format.parse(date));
        voucherMono =Mono.just(voucherDTO);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(Predicate.class),any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<VoucherDTO>>notNull())).thenReturn(voucherMono);

        double response = voucherService.getDiscount("MYNT");
        assertEquals(12.25, response);
    }

    @Test
    void getDiscountWithNull() throws ParseException {
        String date = "2022-12-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(Predicate.class),any())).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<VoucherDTO>>notNull())).thenReturn(voucherMono);

        assertThrows(TryAgainException.class,() -> voucherService.getDiscount("MYNT"));
    }

    @Test
    void getDiscountWithServerDown() throws ParseException {
        String date = "2022-12-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(Predicate.class),any())).thenThrow(WebClientResponseException.class);
        assertThrows(TryAgainException.class,() -> voucherService.getDiscount("MYNT"));
    }

    @Test
    void getDiscountWithInvalidVoucher() throws ParseException {
        String date = "2022-12-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.onStatus(any(Predicate.class),any())).thenThrow(WebClientResponseException.class);
        assertThrows(TryAgainException.class,() -> voucherService.getDiscount("adfas"));
    }

    @Test
    void getDiscountWithTimeout() {
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenThrow(DnsNameResolverTimeoutException.class);
        assertThrows(TryAgainException.class,() -> voucherService.getDiscount("adfas"));
    }

    @Test
    void getDiscountWithEmptyVoucher(){
        double response = voucherService.getDiscount("");
        assertEquals(0, response);
    }

}