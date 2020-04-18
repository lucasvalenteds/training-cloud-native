package io.lucasvalenteds.toll.toll;

import io.lucasvalenteds.toll.payment.Checkout;
import io.lucasvalenteds.toll.vehicle.Vehicle;

public interface Toll {
    public Checkout process(Vehicle vehicle, double money) throws IllegalArgumentException;
}
