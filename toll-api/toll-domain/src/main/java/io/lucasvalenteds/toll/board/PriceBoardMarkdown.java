package io.lucasvalenteds.toll.board;

import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PriceBoardMarkdown implements PriceBoard {
    private final List<Class<? extends Vehicle>> vehicles;

    public PriceBoardMarkdown(List<Class<? extends Vehicle>> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String getBoard() {
        List<String> lines = new ArrayList<>(0);
        lines.add("| Vehicle | Fee | Extra |\n");
        lines.add("| :--- | :--- | :--- |\n");

        vehicles.stream()
            .map(it -> {
                try {
                    Vehicle vehicle = it.newInstance();
                    return Optional.of(new PriceBoardItem(it.getSimpleName(), vehicle.getPrice(), vehicle.getExtra()));
                } catch (InstantiationException | IllegalAccessException e) {
                    return Optional.empty();
                }
            })
            .filter(Optional::isPresent)
            .map(it -> (PriceBoardItem) it.get())
            .map(item -> "| " + item.getVehicleType() + " | " + item.getBaseFee() + " | " + item.getExtraFee() + " |\n")
            .forEach(lines::add);

        return String.join("", lines);
    }
}
