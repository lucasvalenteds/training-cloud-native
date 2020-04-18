package io.lucasvalenteds.calculator.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.calculator.spring.CalculatorConfiguration;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig({CalculatorConfiguration.class, CalculatorWebConfiguration.class})
public class CalculatorWebConfigurationTest {

    @Autowired
    private ObjectMapper objectMapperJson;

    @Autowired
    private CalculatorServlet calculatorServlet;

    @Test
    public void testItExposesJsonMapper() {
        assertNotNull(objectMapperJson);
    }

    @Test
    public void testItExposesCalculatorServlet() {
        assertNotNull(calculatorServlet);
    }
}