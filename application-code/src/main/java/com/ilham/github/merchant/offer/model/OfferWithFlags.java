package com.ilham.github.merchant.offer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferWithFlags {
    private int id;
    private String description;
    private double price;
    private String currency;
    private Date expiryDate;
    private boolean cancelledFlag;
    private boolean expiredFlag;
}
