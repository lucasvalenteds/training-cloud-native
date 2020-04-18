package io.lucasvalenteds.toll.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CheckoutTest {

    @Test
    void testItExposesMoneyReceivedFromCustomer() {
        Checkout checkout = new Checkout(1.0, 2.0);

        assertEquals(1.0, checkout.getReceived());
    }

    @Test
    void testItExposesTheAmountOfMoneyToGiveBackToCustomer() {
        Checkout checkout = new Checkout(5.0, 2.0);

        assertEquals(2.0, checkout.getChange());
    }
}