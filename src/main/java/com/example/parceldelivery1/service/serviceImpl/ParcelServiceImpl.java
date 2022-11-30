package com.example.parceldelivery1.service.serviceImpl;


import com.example.parceldelivery1.dto.Billing;
import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.dto.VoucherDTO;
import com.example.parceldelivery1.helper.DataCalculator;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private DataCalculator dataCalculator;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${voucher-service.host}")
    private String url;


    /**
     * This is a service layer method which will use helper method to provide the output .
     * This is a method which is responsible for providing order details and final cost.
     *
     * @param Parcel parcel,String voucher
     * @return FinalParcel
     */
    @Override
    public FinalParcel placeOrder(Parcel parcel, String voucher) {
        log.info("Calculation for your order is initiated");
        return new FinalParcel(parcel, getFinalCost(parcel, voucher));
    }

    /**
     * This is a method which is responsible for providing discount as per the voucher code provided by User.
     * If voucher code is not provided by user then it will return Zero.
     *
     * @param String voucher
     * @return int
     */
    private long getDiscount(String voucher) {
        if (!voucher.isBlank()) {

            try {
                //VoucherDTO data = restTemplate.getForObject(url + voucher, VoucherDTO.class);
                VoucherDTO data = webClientBuilder.build()
                        .get()
                        .uri(url + voucher)
                        .retrieve()
                        .bodyToMono(VoucherDTO.class)
                        .block();
                if(data != null) {
                    log.info("applied discount successfully: ");
                    return data.getAmount();
                }else
                    throw new NullPointerException("Voucher data is null");
            } catch (ResourceAccessException r) {
                r.printStackTrace();
            }catch (WebClientResponseException w) {
                log.error("Invalid Voucher code");
                w.printStackTrace();
            }
        }
        log.info("discount is not-applied ");
        return 0;
    }

    /**
     * This is a method which is responsible for calculating final cost on the basis of weight or volume.
     * If voucher code is provided by user then it will give final cost with discount.
     *
     * @param Parcel parcel,String voucher
     * @return Billing
     */
    private Billing getFinalCost(Parcel parcel, String voucher) {
        Billing billing = dataCalculator.calculateCost(parcel);
        double finalCost = billing.getCost() - getDiscount(voucher);
        if (finalCost <= 0) {
            billing.setCost(0);
            return billing;
        }
        billing.setCost(finalCost);
        log.info("Billing is done !!");
        return billing;
    }
}
