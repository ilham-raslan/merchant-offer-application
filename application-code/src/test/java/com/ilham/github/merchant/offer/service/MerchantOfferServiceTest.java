package com.ilham.github.merchant.offer.service;

import com.ilham.github.merchant.offer.model.Offer;
import com.ilham.github.merchant.offer.model.OfferInput;
import com.ilham.github.merchant.offer.repository.MerchantOfferRepository;
import com.ilham.github.merchant.offer.util.MockDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class MerchantOfferServiceTest {

    @InjectMocks
    @Spy
    private MerchantOfferService merchantOfferService;

    @Mock
    private MerchantOfferRepository merchantOfferRepository;

    @Test
    void postOfferTest() throws ParseException {
        OfferInput mockOfferInput = MockDataService.generateMockOfferInput();
        Offer mockOffer = MockDataService.generateMockOffer();

        Mockito.doReturn(mockOffer).when(merchantOfferService).getOfferFromOfferInput(any());
        Mockito.when(merchantOfferRepository.save(any())).thenReturn(mockOffer);
        assertEquals("Offer successfully made",merchantOfferService.postOffer(mockOfferInput));
    }

    @Test
    void getOfferFromOfferInputTest() throws ParseException {
        assertEquals(MockDataService.generateMockOfferFromMockOfferInput(),merchantOfferService.getOfferFromOfferInput(MockDataService.generateMockOfferInput()));
    }

    @Test
    void getAllOffersTest() throws ParseException {
        List<Offer> mockOfferList = MockDataService.generateMockListOfOffers();
        Mockito.when(merchantOfferRepository.findAll()).thenReturn(mockOfferList);
        assertEquals(mockOfferList,merchantOfferService.getAllOffers());
    }

    @Test
    void cancelOfferTest() {
        Mockito.when(merchantOfferRepository.findById(anyInt())).thenReturn(MockDataService.generateMockOptionalOffer());
        Mockito.when(merchantOfferRepository.save(any())).thenReturn(null);
        assertEquals("Cancel successfully made",merchantOfferService.cancelOffer(1));
    }

    @Test
    void cancelExpiredOfferThrowsInvalidArgumentExceptionTest() {
        Mockito.when(merchantOfferRepository.findById(anyInt())).thenReturn(MockDataService.generateMockEmptyOptional());
        assertThrows(IllegalArgumentException.class,() -> merchantOfferService.cancelOffer(1));
    }

    @Test
    void cancelCancelledOfferThrowsInvalidArgumentExceptionTest() {
        Mockito.when(merchantOfferRepository.findById(anyInt())).thenReturn(MockDataService.generateMockCancelledOptionalOffer());
        assertThrows(IllegalArgumentException.class,() -> merchantOfferService.cancelOffer(1));
    }

    @Test
    void cancelNonExistentOfferThrowsInvalidArgumentExceptionTest() {
        Mockito.when(merchantOfferRepository.findById(anyInt())).thenReturn(MockDataService.generateMockExpiredOptionalOffer());
        assertThrows(IllegalArgumentException.class,() -> merchantOfferService.cancelOffer(1));
    }
}