package com.example.parceldelivery1.controller;


import com.example.parceldelivery1.constants.ControllerMessage;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "parcel")
@Validated
public class OrderController {

    @Autowired
    private Logger logger;
    @Autowired
    private ParcelService parcelService;

//    @PostMapping("/order")
//    public ResponseEntity getOrder(@Valid Parcel parcel,
//                                   @RequestParam(required = false, defaultValue = "") String voucher){
//        return new ResponseEntity<>(parcelService.placeOrder(parcel,voucher), HttpStatus.ACCEPTED);
//    }

    @GetMapping("/order")
    public ResponseEntity getOrder(@RequestParam("weight")
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
        logger.info("Order is collected!!");
        return new ResponseEntity<>(parcelService.placeOrder(parcel,voucher), HttpStatus.OK);
    }
}
