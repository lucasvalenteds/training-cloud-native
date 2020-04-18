package io.lucasvalenteds.toll.payment;

import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.function.Function;

public interface VehicleFee extends Function<Vehicle, Double> {
}
