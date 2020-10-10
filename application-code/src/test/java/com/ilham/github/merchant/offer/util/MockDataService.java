package com.ilham.github.merchant.offer.util;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.model.OfferInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MockDataService {
    public static Offer generateMockOffer() {
        return new Offer();
    }

    public static Offer generateMockOfferFromMockOfferInput() throws ParseException {
        return new Offer(0,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/MM/yy").parse("01/02/2021"),false,false);
    }

    public static OfferInput generateMockOfferInput() throws ParseException {
        return new OfferInput("DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/MM/yy").parse("01/02/2021"));
    }

    public static List<Offer> generateMockListOfOffers() throws ParseException {
        List<Offer> mockListOfOffers = new ArrayList<>();

        mockListOfOffers.add(new Offer(1,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/MM/yy").parse("01/02/2021"),false,false));
        mockListOfOffers.add(new Offer(2,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/MM/yy").parse("02/02/2021"),false,false));
        mockListOfOffers.add(new Offer(3,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/MM/yy").parse("03/02/2021"),false,false));
        mockListOfOffers.add(new Offer(3,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/MM/yy").parse("03/02/2018"),false,false));

        return mockListOfOffers;
    }

    public static Optional<Offer> generateMockOptionalOffer() {
        Offer offer = new Offer(1,"DUMMY",1.23,"DUMMY", new Date(new Date().getTime() + (1000 * 60 * 60 * 24)),false,false);
        return Optional.of(offer);
    }

    public static Optional<Offer> generateMockExpiredOptionalOffer() {
        Offer offer = new Offer(1,"DUMMY",1.23,"DUMMY", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)),false,false);
        return Optional.of(offer);
    }

    public static Optional<Offer> generateMockCancelledOptionalOffer() {
        Offer offer = new Offer(1,"DUMMY",1.23,"DUMMY", new Date(new Date().getTime() + (1000 * 60 * 60 * 24)),true,false);
        return Optional.of(offer);
    }

    public static Optional<Offer> generateMockEmptyOptional() {
        return Optional.empty();
    }
}
