package io.lucasvalenteds.toll.payment;

import io.lucasvalenteds.toll.vehicle.Truck;

public class VehicleFees {
    public static VehicleFee defaultFee = vehicle -> {
        if (vehicle instanceof Truck) {
            return 0.0;
        } else {
            return vehicle.getPrice();
        }
    };

    public static VehicleFee truckFee = vehicle -> {
        if (vehicle instanceof Truck) {
            Truck truck = (Truck) vehicle;
            return truck.getPrice() + (truck.getNumberOfAxles() * truck.getExtra());
        } else {
            return 0.0;
        }
    };
}
