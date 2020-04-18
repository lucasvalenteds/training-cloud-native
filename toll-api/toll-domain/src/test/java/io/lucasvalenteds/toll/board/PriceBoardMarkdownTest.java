package io.lucasvalenteds.toll.board;

import io.lucasvalenteds.toll.vehicle.Truck;
import io.lucasvalenteds.toll.vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriceBoardMarkdownTest {

    private final List<Class<? extends Vehicle>> vehicles = new ArrayList<>(0);
    private final PriceBoard board = new PriceBoardMarkdown(vehicles);

    @BeforeEach
    public void setUp() {
        this.vehicles.add(Truck.class);
    }

    @Test
    public void testItShowsVehicleTypeFeeAndExtraFee() {
        String board = this.board.getBoard();

        Vehicle truck = new Truck();
        assertTrue(board.contains(truck.getClass().getSimpleName()));
        assertTrue(board.contains("" + truck.getPrice()));
        assertTrue(board.contains("" + truck.getExtra()));
    }
}