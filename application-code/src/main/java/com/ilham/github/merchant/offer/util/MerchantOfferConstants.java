package com.ilham.github.merchant.offer.util;

/**
 * Constants class for key messages and other relevant constants
 */

public class MerchantOfferConstants {

    private MerchantOfferConstants() {}

    public static final String OFFER_SUCCESSFULLY_MADE_MESSAGE = "Offer successfully made";
    public static final String CANCEL_SUCCESSFULLY_MADE_MESSAGE = "Cancel successfully made";
    public static final String CANCEL_NONEXISTENT_OFFER_LOG_MESSAGE = "User has requested to cancel an offer that does not exist, throwing exception";
    public static final String CANCEL_CANCELLED_OFFER_LOG_MESSAGE = "User has requested to cancel an offer that has already been cancelled, throwing exception";
    public static final String CANCEL_EXPIRED_OFFER_LOG_MESSAGE = "User has requested to cancel an offer that has already expired, throwing exception";
    public static final String CANCEL_NONEXISTENT_OFFER_EXCEPTION_MESSAGE = "Offer cancellation failed as an offer with that id does not exist";
    public static final String CANCEL_CANCELLED_OFFER_EXCEPTION_MESSAGE = "Offer cancellation failed as the offer has already been cancelled";
    public static final String CANCEL_EXPIRED_OFFER_EXCEPTION_MESSAGE = "Offer cancellation failed as the offer has already expired";
    public static final String EXCEPTION_RESPONSE_MESSAGE = "Error occurred with message: ";
}
