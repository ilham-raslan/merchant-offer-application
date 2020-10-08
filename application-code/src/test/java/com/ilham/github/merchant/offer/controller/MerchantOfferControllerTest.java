package com.ilham.github.merchant.offer.controller;

import com.ilham.github.merchant.offer.service.MerchantOfferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = MerchantOfferController.class)
@ExtendWith(MockitoExtension.class)
class MerchantOfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private MerchantOfferController merchantOfferController;

    @Mock
    private MerchantOfferService merchantOfferService;

    @Test
    void postOfferTest() {

    }

    @Test
    void getAllOffersTest() {

    }

    @Test
    void cancelOfferTest() {

    }

    @Test
    void cancelExpiredOfferTest() {

    }

    @Test
    void cancelNonExistentOfferTest() {

    }
}