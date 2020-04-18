package io.lucasvalenteds.toll.toll;

import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VehicleSensor {

    private List<Class<? extends Vehicle>> typesOfVehiclesAllowedToPass;

    public VehicleSensor(List<Class<? extends Vehicle>> typesOfVehiclesAllowedToPass) {
        this.typesOfVehiclesAllowedToPass = typesOfVehiclesAllowedToPass;
    }

    public boolean isVehicleOfType(Vehicle vehicle, Class<? extends Vehicle> type) {
        return type.isInstance(vehicle);
    }

    public Optional<Vehicle> isVehicleAllowedToPass(String vehicleIdentifier) {
        return typesOfVehiclesAllowedToPass.stream()
            .filter(it -> it.getSimpleName().toLowerCase().equals(vehicleIdentifier))
            .map(it -> {
                try {
                    return (Vehicle) it.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .findFirst();
    }
}
