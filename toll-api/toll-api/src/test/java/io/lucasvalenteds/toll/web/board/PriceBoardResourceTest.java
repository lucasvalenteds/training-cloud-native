package io.lucasvalenteds.toll.web.board;

import io.lucasvalenteds.toll.web.config.DomainConfiguration;
import io.lucasvalenteds.toll.web.config.WebConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig({DomainConfiguration.class, WebConfiguration.class})
public class PriceBoardResourceTest {

    @Autowired
    private PriceBoardResource resource;

    @Test
    public void testItPresentsPricesAsTable() {
        String board = resource.show();

        assertEquals(
            "| Vehicle | Fee | Extra |\n" +
                "| :--- | :--- | :--- |\n" +
                "| Bus | 1.59 | 0.0 |\n" +
                "| Bike | 0.49 | 0.0 |\n" +
                "| Fusca | 2.11 | 0.0 |\n" +
                "| Motorcycle | 1.0 | 0.0 |\n" +
                "| Truck | 3.95 | 0.5 |\n"
            , board);
    }
}