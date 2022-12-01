package com.example.parceldelivery1.controller;


import com.example.parceldelivery1.dto.Amount;
import com.example.parceldelivery1.model.Parcel;
import com.example.parceldelivery1.service.ParcelService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "parcel")
@Slf4j
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/getCost")
    @RolesAllowed("user")
    public ResponseEntity<Amount> getCost(@RequestBody @Valid Parcel parcel,
                                          @RequestParam(required = false, defaultValue = "") String voucher){
        log.info("Order is collected!!");
        return new ResponseEntity<>(parcelService.getCost(parcel,voucher), HttpStatus.OK);
    }
}
