package com.ilham.github.merchant.offer.service;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.model.OfferWithFlags;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantOfferService {
    public ResponseEntity<String> postOffer(Offer offer) {
        return null;
    }

    public ResponseEntity<List<OfferWithFlags>> getAllOffers() {
        return null;
    }

    public ResponseEntity<String> cancelOffer(int id) {
        return null;
    }
}
