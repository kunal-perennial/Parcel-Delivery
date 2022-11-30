package com.example.parceldelivery1.controller;


import com.example.parceldelivery1.constants.ControllerMessage;
import com.example.parceldelivery1.dto.FinalParcel;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "parcel")
@Validated
@Slf4j
public class OrderController {

    @Autowired
    private ParcelService parcelService;

    @GetMapping("/order")
    public ResponseEntity<FinalParcel> getOrder(@RequestParam("weight")
                                       @Positive(message = ControllerMessage.POSITIVE_WEIGHT_MESSAGE)
                                       @Max(value = 50, message = ControllerMessage.MAXIMUM_WEIGHT_MESSAGE)
                                       Integer weight,

                                       @RequestParam("height")
                                       @Positive(message = ControllerMessage.POSITIVE_HEIGHT_MESSAGE)
                                       @NotNull(message = ControllerMessage.NOTNULL_HEIGHT_MESSAGE)
                                       Integer height,

                                       @RequestParam("width")
                                       @Positive(message = ControllerMessage.POSITIVE_WIDTH_MESSAGE)
                                       @NotNull(message = ControllerMessage.NOTNULL_WIDTH_MESSAGE)
                                       Integer width,

                                       @RequestParam("length")
                                       @Positive(message = ControllerMessage.POSITIVE_LENGTH_MESSAGE)
                                       @NotNull(message = ControllerMessage.NOTNULL_LENGTH_MESSAGE)
                                       Integer length,

                                       @RequestParam(required = false, defaultValue = "") String voucher){
        Parcel parcel = new Parcel(weight,height,width,length);
        log.info("Order is collected!!");
        return new ResponseEntity<>(parcelService.placeOrder(parcel,voucher), HttpStatus.OK);
    }
}
