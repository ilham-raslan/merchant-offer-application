package com.ilham.github.merchant.offer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * OfferInput model class to be populated by the merchant
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OfferInput {
    private String description;
    private double price;
    private String currency;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date expiryDate;
}
