package io.lucasvalenteds.toll.web.config;

import io.lucasvalenteds.toll.board.PriceBoard;
import io.lucasvalenteds.toll.board.PriceBoardMarkdown;
import io.lucasvalenteds.toll.payment.VehicleFee;
import io.lucasvalenteds.toll.payment.VehicleFees;
import io.lucasvalenteds.toll.toll.SemParar;
import io.lucasvalenteds.toll.toll.Toll;
import io.lucasvalenteds.toll.toll.VehicleSensor;
import io.lucasvalenteds.toll.vehicle.Bike;
import io.lucasvalenteds.toll.vehicle.Bus;
import io.lucasvalenteds.toll.vehicle.Fusca;
import io.lucasvalenteds.toll.vehicle.Motorcycle;
import io.lucasvalenteds.toll.vehicle.Truck;
import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public List<Class<? extends Vehicle>> vehicles() {
        List<Class<? extends Vehicle>> vehicles = new ArrayList<>(0);
        vehicles.add(Bus.class);
        vehicles.add(Bike.class);
        vehicles.add(Fusca.class);
        vehicles.add(Motorcycle.class);
        vehicles.add(Truck.class);
        return vehicles;
    }

    @Bean
    public VehicleSensor vehicleSensor(List<Class<? extends Vehicle>> vehicles) {
        return new VehicleSensor(vehicles);
    }

    @Bean
    public List<VehicleFee> fees() {
        List<VehicleFee> fees = new ArrayList<>(0);
        fees.add(VehicleFees.defaultFee);
        fees.add(VehicleFees.truckFee);
        return fees;
    }

    @Bean
    public Toll toll(List<VehicleFee> fees) {
        return new SemParar(fees);
    }

    @Bean
    public PriceBoard priceBoardFormatted(List<Class<? extends Vehicle>> vehicles) {
        return new PriceBoardMarkdown(vehicles);
    }
}
