package io.lucasvalenteds.toll.toll;

import io.lucasvalenteds.toll.payment.Checkout;
import io.lucasvalenteds.toll.payment.VehicleFee;
import io.lucasvalenteds.toll.payment.VehicleFees;
import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SemPararTest {

    private final List<VehicleFee> fees = new ArrayList<>(0);
    private final Toll toll = new SemParar(fees);

    @BeforeEach
    public void setUp() {
        fees.add(VehicleFees.defaultFee);
        fees.add(VehicleFees.truckFee);
    }

    @Test
    public void testItCalculatesTheChangeToBeGivenToTheCustomer() {
        Vehicle vehicle = new VehicleWithOneDollarFee();

        Checkout checkout = toll.process(vehicle, 1.5);

        assertEquals(1.5, checkout.getReceived());
        assertEquals(0.5, checkout.getChange());
    }

    @Test
    public void testItFailsToProcessIfCustomerGivesLessMoneyThanRequired() {
        Vehicle vehicle = new VehicleWithOneDollarFee();

        assertThrows(
            IllegalArgumentException.class,
            () -> toll.process(vehicle, 0.2),
            "Money received is not enough. It was received %0.2 but should be at least 1.0"
        );
    }

    private static class VehicleWithOneDollarFee implements Vehicle {
        @Override
        public double getPrice() {
            return 1.0;
        }
    }

}