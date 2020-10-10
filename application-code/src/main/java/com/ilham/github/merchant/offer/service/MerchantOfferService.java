package com.ilham.github.merchant.offer.service;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.model.OfferInput;
import com.ilham.github.merchant.offer.repository.MerchantOfferRepository;
import com.ilham.github.merchant.offer.util.MerchantOfferConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service class containing logic of the application communicating with the database table
 */

@Slf4j
@Service
public class MerchantOfferService {

    @Autowired
    private MerchantOfferRepository merchantOfferRepository;

    /**
     * Method to post a new offer into the database table
     * @param offerInput OfferInput populated by the merchant
     * @return Offer successfully made message
     */

    public String postOffer(OfferInput offerInput) {
        Offer offer = getOfferFromOfferInput(offerInput);
        merchantOfferRepository.save(offer);
        return MerchantOfferConstants.OFFER_SUCCESSFULLY_MADE_MESSAGE;
    }

    /**
     * Helper method to transform the OfferInput from the merchant into the Offer written to the database table
     * @param offerInput OfferInput populated by the merchant
     * @return The corresponding Offer entity of the OfferInput
     */

    public Offer getOfferFromOfferInput(OfferInput offerInput) {
        Offer offer = new Offer();

        offer.setDescription(offerInput.getDescription());
        offer.setPrice(offerInput.getPrice());
        offer.setCurrency(offerInput.getCurrency());
        offer.setExpiryDate(offerInput.getExpiryDate());

        return offer;
    }

    /**
     * Method to get all the offers in the database, including flags for cancelled and expired orders
     * @return List of all offers made, including flags for cancelled and expired orders
     */

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

    /**
     * Method to cancel an offer in the database table based on its Id
     * @param id Id of the offer to be cancelled
     * @return Cancel successfully made message
     */

    public String cancelOffer(int id) {
        Optional<Offer> optionalOffer = merchantOfferRepository.findById(id);
        if (!optionalOffer.isPresent()) {
            log.info(MerchantOfferConstants.CANCEL_NONEXISTENT_OFFER_LOG_MESSAGE);
            throw new IllegalArgumentException(MerchantOfferConstants.CANCEL_NONEXISTENT_OFFER_EXCEPTION_MESSAGE);
        }

        Offer offer = optionalOffer.get();

        if (offer.isCancelledFlag()) {
            log.info(MerchantOfferConstants.CANCEL_CANCELLED_OFFER_LOG_MESSAGE);
            throw new IllegalArgumentException(MerchantOfferConstants.CANCEL_CANCELLED_OFFER_EXCEPTION_MESSAGE);
        }

        if ((new Date()).after(offer.getExpiryDate())) {
            log.info(MerchantOfferConstants.CANCEL_EXPIRED_OFFER_LOG_MESSAGE);
            throw new IllegalArgumentException(MerchantOfferConstants.CANCEL_EXPIRED_OFFER_EXCEPTION_MESSAGE);
        }

        offer.setCancelledFlag(true);
        merchantOfferRepository.save(offer);

        return MerchantOfferConstants.CANCEL_SUCCESSFULLY_MADE_MESSAGE;
    }
}
