package com.ilham.github.merchant.offer.controller;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.model.OfferWithFlags;
import com.ilham.github.merchant.offer.service.MerchantOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MerchantOfferController {
    @Autowired
    private MerchantOfferService merchantOfferService;

    @PostMapping("/offer")
    public ResponseEntity<String> postOffer(@RequestBody Offer offer) {
        return merchantOfferService.postOffer(offer);
    }

    @GetMapping("/offer")
    public ResponseEntity<List<OfferWithFlags>> getAllOffers() {
        return merchantOfferService.getAllOffers();
    }

    @PostMapping("/offer/cancel")
    public ResponseEntity<String> cancelOffer(int id) {
        return merchantOfferService.cancelOffer(id);
    }
}
