package com.ilham.github.merchant.offer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilham.github.merchant.offer.service.MerchantOfferService;
import com.ilham.github.merchant.offer.util.MockDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MerchantOfferController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class MerchantOfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private MerchantOfferController merchantOfferController;

    @MockBean
    private MerchantOfferService merchantOfferService;

    @Test
    void postOfferTest() throws Exception {
        Mockito.when(merchantOfferService.postOffer(any())).thenReturn("Offer successfully made");
        mockMvc.perform(post("/offer/post")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(MockDataService.generateMockOffer())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Offer successfully made")));
    }

    @Test
    void getAllOffersTest() throws Exception {
        Mockito.when(merchantOfferService.getAllOffers()).thenReturn(MockDataService.generateMockListOfOffers());
        mockMvc.perform(get("/offer/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("01/02/2021")))
                .andExpect(content().string(containsString("02/02/2021")))
                .andExpect(content().string(containsString("03/02/2021")));
    }

    @Test
    void cancelOfferTest() throws Exception {
        Mockito.when(merchantOfferService.cancelOffer(anyInt())).thenReturn("Cancel successfully made");
        mockMvc.perform(post("/offer/cancel/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cancel successfully made")));
    }

    @Test
    void cancelExpiredOfferTest() throws Exception {
        Mockito.when(merchantOfferService.cancelOffer(anyInt())).thenThrow(new IllegalArgumentException("Offer cancellation failed as the offer has already expired"));
        mockMvc.perform(post("/offer/cancel/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Offer cancellation failed as the offer has already expired")));
    }

    @Test
    void cancelNonExistentOfferTest() throws Exception {
        Mockito.when(merchantOfferService.cancelOffer(anyInt())).thenThrow(new IllegalArgumentException("Offer cancellation failed as an offer with that id does not exist"));
        mockMvc.perform(post("/offer/cancel/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Offer cancellation failed as an offer with that id does not exist")));
    }

    @Test
    void cancelOfferThrowsRuntimeException() throws Exception {
        Mockito.when(merchantOfferService.cancelOffer(anyInt())).thenThrow(new RuntimeException("Generic Runtime Exception"));
        mockMvc.perform(post("/offer/cancel/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("Generic Runtime Exception")));
    }
}