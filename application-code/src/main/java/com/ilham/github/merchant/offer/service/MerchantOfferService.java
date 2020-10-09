package com.ilham.github.merchant.offer.service;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.repository.MerchantOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantOfferService {

    @Autowired
    private MerchantOfferRepository merchantOfferRepository;

    public String postOffer(Offer offer) {
        merchantOfferRepository.save(offer);
        return "Offer successfully made";
    }

    public List<Offer> getAllOffers() {
        return (List<Offer>) merchantOfferRepository.findAll();
    }

    public String cancelOffer(int id) {
        Optional<Offer> optionalOffer = merchantOfferRepository.findById(id);
        if (!optionalOffer.isPresent()) {
            throw new IllegalArgumentException("Offer cancellation failed as an offer with that id does not exist");
        }

        Offer offer = optionalOffer.get();

        if (offer.getExpiryDate().after(new Date())) {
            throw new IllegalArgumentException("Offer cancellation failed as the offer has already expired");
        }

        offer.setCancelledFlag(true);
        merchantOfferRepository.save(offer);

        return "Cancel successfully made";
    }
}
