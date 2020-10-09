package com.ilham.github.merchant.offer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class MerchantOfferApplicationTests {

	@Test
	void contextLoads() {
		assertAll(()->MerchantOfferApplication.main(new String[]{}));
	}

}
