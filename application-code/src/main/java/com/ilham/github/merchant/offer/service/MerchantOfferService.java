package com.ilham.github.merchant.offer.service;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.model.OfferInput;
import com.ilham.github.merchant.offer.repository.MerchantOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MerchantOfferService {

    @Autowired
    private MerchantOfferRepository merchantOfferRepository;

    public String postOffer(OfferInput offerInput) {
        Offer offer = getOfferFromOfferInput(offerInput);
        merchantOfferRepository.save(offer);
        return "Offer successfully made";
    }

    public Offer getOfferFromOfferInput(OfferInput offerInput) {
        Offer offer = new Offer();

        offer.setDescription(offerInput.getDescription());
        offer.setPrice(offerInput.getPrice());
        offer.setCurrency(offerInput.getCurrency());
        offer.setExpiryDate(offerInput.getExpiryDate());

        return offer;
    }

    public List<Offer> getAllOffers() {
        List<Offer> currentOfferList = (List<Offer>) merchantOfferRepository.findAll();
        List<Offer> modifiedOfferList = new ArrayList<>();

        for (Offer offer : currentOfferList) {
            if ((new Date()).after(offer.getExpiryDate())) {
                offer.setExpiredFlag(true);
            }
            modifiedOfferList.add(offer);
        }

        return modifiedOfferList;
    }

    public String cancelOffer(int id) {
        Optional<Offer> optionalOffer = merchantOfferRepository.findById(id);
        if (!optionalOffer.isPresent()) {
            log.info("User has requested to cancel an offer that does not exist, throwing exception");
            throw new IllegalArgumentException("Offer cancellation failed as an offer with that id does not exist");
        }

        Offer offer = optionalOffer.get();

        if (offer.isCancelledFlag()) {
            log.info("User has requested to cancel an offer that has already been cancelled, throwing exception");
            throw new IllegalArgumentException("Offer cancellation failed as the offer has already been cancelled");
        }

        if ((new Date()).after(offer.getExpiryDate())) {
            log.info("User has requested to cancel an offer that has already expired, throwing exception");
            throw new IllegalArgumentException("Offer cancellation failed as the offer has already expired");
        }

        offer.setCancelledFlag(true);
        merchantOfferRepository.save(offer);

        return "Cancel successfully made";
    }
}
