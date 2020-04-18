package io.lucasvalenteds.toll.payment;

import io.lucasvalenteds.toll.vehicle.Bike;
import io.lucasvalenteds.toll.vehicle.Truck;
import io.lucasvalenteds.toll.vehicle.Vehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class VehicleFeesTest {

    @Test
    public void testDefaultFeeReturnsVehiclePriceOnly() {
        Vehicle bike = new Bike();

        assertEquals(bike.getPrice(), VehicleFees.defaultFee.apply(bike));
    }

    @Test
    public void testDefaultFeeDoesNotWorkWithTrucks() {
        assertEquals(0.0, VehicleFees.defaultFee.apply(new Truck(2)));
    }

    @Test
    public void testTruckFeeUsesTheNumberOfAxlesToCalculateExtraFee() {
        Truck singleAxle = new Truck(1);
        Truck multipleAxles = new Truck(2);

        Double singleAxleFee = VehicleFees.truckFee.apply(singleAxle);
        Double multipleAxlesFee = VehicleFees.truckFee.apply(multipleAxles);

        assertNotEquals(singleAxleFee, multipleAxlesFee, 0.1);
    }

    @Test
    public void testTruckFeeWorksWithTrucksOnly() {
        assertEquals(0.0, VehicleFees.truckFee.apply(new Bike()));
    }

}