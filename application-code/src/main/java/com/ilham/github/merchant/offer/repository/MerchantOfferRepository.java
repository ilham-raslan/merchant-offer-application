package com.ilham.github.merchant.offer.repository;

import com.ilham.github.merchant.offer.model.Offer;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for the application to communicate to the database with the Offer table
 */

public interface MerchantOfferRepository extends CrudRepository<Offer,Integer> {
}
