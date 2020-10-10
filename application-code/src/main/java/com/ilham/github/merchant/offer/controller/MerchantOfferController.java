package com.ilham.github.merchant.offer.controller;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.model.OfferInput;
import com.ilham.github.merchant.offer.service.MerchantOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller class to handle merchant requests
 */

@RestController
@RequestMapping
public class MerchantOfferController {
    @Autowired
    private MerchantOfferService merchantOfferService;

    /**
     * Endpoint for the merchant to post a new offer
     * @param offerInput The offer to be placed
     * @return Offer successfully made response entity
     */

    @PostMapping("/offer/post")
    public ResponseEntity<String> postOffer(@RequestBody OfferInput offerInput) {
        return new ResponseEntity<>(merchantOfferService.postOffer(offerInput), HttpStatus.OK);
    }

    /**
     * Endpoint for the merchant to view all offers made, including flags for cancelled and expired orders
     * @return List of all offers made, including flags for cancelled and expired orders
     */

    @GetMapping("/offer/all")
    public ResponseEntity<List<Offer>> getAllOffers() {
        return new ResponseEntity<>(merchantOfferService.getAllOffers(),HttpStatus.OK);
    }


    /**
     * Endpoint for the merchant to cancel an offer based on its id
     * @param id Id of the order to be cancelled
     * @return Cancel successfully made response entity
     */

    @PostMapping("/offer/cancel/{id}")
    public ResponseEntity<String> cancelOffer(@PathVariable int id) {
        return new ResponseEntity<>(merchantOfferService.cancelOffer(id),HttpStatus.OK);
    }
}
