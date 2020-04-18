package io.lucasvalenteds.toll.toll;

import io.lucasvalenteds.toll.vehicle.Bike;
import io.lucasvalenteds.toll.vehicle.Truck;
import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehicleSensorTest {

    private final List<Class<? extends Vehicle>> vehicles = new ArrayList<>(0);
    private final VehicleSensor sensor = new VehicleSensor(vehicles);

    @BeforeEach
    public void setUp() {
        this.vehicles.add(Bike.class);
        this.vehicles.add(Truck.class);
    }

    @Test
    public void testIsVehicleTypeWorks() {
        Vehicle vehicle = new Truck();

        assertTrue(sensor.isVehicleOfType(vehicle, Truck.class));
        assertFalse(sensor.isVehicleOfType(vehicle, Bike.class));
    }

    @Test
    public void testItReturnsTheVehicleByType() {
        Optional<Vehicle> possibleVehicle = sensor.isVehicleAllowedToPass("truck");

        assertTrue(this.vehicles.contains(Truck.class));
        assertTrue(possibleVehicle.isPresent());
        assertTrue(possibleVehicle.get() instanceof Truck);
    }
}