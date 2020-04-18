package io.lucasvalenteds.toll.toll;

import io.lucasvalenteds.toll.payment.Checkout;
import io.lucasvalenteds.toll.payment.VehicleFee;
import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.math.BigDecimal;
import java.util.List;

public class SemParar implements Toll {

    private final List<VehicleFee> fees;

    public SemParar(List<VehicleFee> fees) {
        this.fees = fees;
    }

    @Override
    public Checkout process(Vehicle vehicle, double money) {
        double feeToPay = fees.stream()
            .mapToDouble(fee -> fee.apply(vehicle))
            .sum();

        double change = BigDecimal.valueOf(money)
            .subtract(BigDecimal.valueOf(feeToPay))
            .setScale(2)
            .doubleValue();

        if (change >= 0) {
            return new Checkout(money, change);
        } else {
            throw new IllegalArgumentException(
                "Money received is not enough. It was received " + money + " but should be at least " + feeToPay
            );
        }
    }
}
