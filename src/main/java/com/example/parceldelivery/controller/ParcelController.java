package com.example.parceldelivery.controller;



import com.example.parceldelivery.model.Parcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.parceldelivery.service.ParcelService;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "parcel")
@Slf4j
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/getCost")
    //@RolesAllowed({"user"})
    public ResponseEntity<Double> getCost(@RequestBody @Valid Parcel parcel,
                                          @RequestParam(required = false, defaultValue = "") String voucher){
        log.info("Order is collected!!");
        return new ResponseEntity<>(parcelService.getCost(parcel,voucher), HttpStatus.OK);
    }
}
