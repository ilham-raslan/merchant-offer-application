package com.ilham.github.merchant.offer.util;

import com.ilham.github.merchant.offer.model.Offer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MockDataService {
    public static Offer generateMockOffer() {
        return new Offer();
    }

    public static List<Offer> generateMockListOfOffers() throws ParseException {
        List<Offer> mockListOfOffers = new ArrayList<>();

        mockListOfOffers.add(new Offer(1,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/mm/yy").parse("01/02/2021"),false,false));
        mockListOfOffers.add(new Offer(2,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/mm/yy").parse("02/02/2021"),false,false));
        mockListOfOffers.add(new Offer(3,"DUMMY",1.23,"DUMMY",new SimpleDateFormat("dd/mm/yy").parse("03/02/2021"),false,false));

        return mockListOfOffers;
    }

    public static Optional<Offer> generateMockOptionalOffer() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);

        Offer offer = new Offer(1,"DUMMY",1.23,"DUMMY", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)),false,false);

        return Optional.of(offer);
    }

    public static Optional<Offer> generateMockExpiredOptionalOffer() {
        Offer offer = new Offer(1,"DUMMY",1.23,"DUMMY", new Date(new Date().getTime() + (1000 * 60 * 60 * 24)),false,false);

        return Optional.of(offer);
    }

    public static Optional<Offer> generateMockEmptyOptional() {
        return Optional.empty();
    }
}
