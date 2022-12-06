package com.example.parceldelivery.service.serviceimpl;

import com.example.parceldelivery.custom.exceptions.TryAgainException;
import com.example.parceldelivery.dto.VoucherDTO;
import com.example.parceldelivery.service.VoucherService;
import io.netty.resolver.dns.DnsNameResolverException;
import io.netty.resolver.dns.DnsNameResolverTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Slf4j
public class VoucherServiceImpl implements VoucherService {

    /**
     * This is a method which is responsible for providing discount as per the voucher code provided by User.
     * If voucher code is not provided by user then it will return Zero.
     *
     * @param voucher
     * @return int
     */
    @Autowired
    private WebClient webClientBuilder;

    @Value("${voucher-service.host}")
    private String url;

    public double getDiscount(String voucher) {
        log.info("Inside the Voucher Service");
        if (!voucher.isBlank()) {

            try {
                log.info(url+voucher+"?key=apikey");
                Mono<VoucherDTO> data = webClientBuilder
                        .get()
                        .uri(url+voucher+"?key=apikey")
                        .retrieve()
                        .onStatus(HttpStatus::is5xxServerError, ClientResponse::createException)
                        .bodyToMono(VoucherDTO.class);
                    log.info("applied discount successfully: ");
                    return Objects.requireNonNull(data.block()).getDiscount();
            } catch (NullPointerException n) {
                log.error(n.getMessage());
                n.printStackTrace();
                throw new TryAgainException("Please try again !!!");
            } catch (WebClientResponseException w) {
                log.error("Server Down");
                w.printStackTrace();
                throw new TryAgainException("Something went wrong !!!");
            } catch (DnsNameResolverTimeoutException d){
                log.error("Timeout!!!!");
                d.printStackTrace();
                throw new TryAgainException("Please try again !!!");
            }
        }
        log.info("discount is not-applied ");
        return 0;
    }
}
