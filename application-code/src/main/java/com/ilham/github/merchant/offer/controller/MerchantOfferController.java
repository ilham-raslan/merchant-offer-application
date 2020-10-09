package com.ilham.github.merchant.offer.controller;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.service.MerchantOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MerchantOfferController {
    @Autowired
    private MerchantOfferService merchantOfferService;

    @PostMapping("/offer/post")
    public ResponseEntity<String> postOffer(@RequestBody Offer offer) {
        return new ResponseEntity<>(merchantOfferService.postOffer(offer), HttpStatus.OK);
    }

    @GetMapping("/offer/all")
    public ResponseEntity<List<Offer>> getAllOffers() {
        return new ResponseEntity<>(merchantOfferService.getAllOffers(),HttpStatus.OK);
    }

    @PostMapping("/offer/cancel/{id}")
    public ResponseEntity<String> cancelOffer(@PathVariable int id) {
        return new ResponseEntity<>(merchantOfferService.cancelOffer(id),HttpStatus.OK);
    }
}
