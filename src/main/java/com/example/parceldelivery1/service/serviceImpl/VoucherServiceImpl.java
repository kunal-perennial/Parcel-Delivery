package com.example.parceldelivery1.service.serviceImpl;

import com.example.parceldelivery1.dto.VoucherDTO;
import com.example.parceldelivery1.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
    private WebClient.Builder webClientBuilder;

    @Value("${voucher-service.host}")
    private String url;

    public double getDiscount(String voucher) {
        if (!voucher.isBlank()) {

            try {
                log.info(url+voucher+"?key=apikey");
                VoucherDTO data = webClientBuilder.build()
                        .get()
                        .uri(url+voucher+"?key=apikey")
                        .retrieve()
                        .bodyToMono(VoucherDTO.class)
                        .block();
                if(data != null) {
                    log.info("applied discount successfully: ");
                    return data.getDiscount();
                }else
                    throw new NullPointerException("Voucher data is null");
            } catch (ResourceAccessException r) {
                log.error(r.getMessage());
                r.printStackTrace();
            }catch (WebClientResponseException.BadRequest w) {
                log.error("Invalid Voucher code");
                w.printStackTrace();
            }
        }
        log.info("discount is not-applied ");
        return 0;
    }
}
