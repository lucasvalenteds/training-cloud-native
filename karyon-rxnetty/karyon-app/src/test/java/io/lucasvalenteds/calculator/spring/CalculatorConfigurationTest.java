package io.lucasvalenteds.calculator.spring;

import io.lucasvalenteds.calculator.Calculator;
import io.lucasvalenteds.calculator.Operation;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(CalculatorConfiguration.class)
public class CalculatorConfigurationTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void testInjectionProgrammatically() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("io.lucasvalenteds.calculator");
        context.refresh();

        assertNotNull(context.getBean(Calculator.class));
    }

    @Test
    public void testInjectionViaAnnotations() {
        assertNotNull(calculator);
    }

    @Test
    public void testEachCalculatorInstanceHasItsOwnMemory() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CalculatorConfiguration.class);
        context.refresh();

        Calculator calculatorHp = new Calculator(context, new ArrayList<>(0));
        Calculator calculatorCasio = new Calculator(context, new ArrayList<>(0));

        calculatorHp.evaluate("pow", 2.0, 5.0);
        calculatorCasio.evaluate("sum", 2.0, 3.0);
        calculatorCasio.evaluate("div", 25.0, 10.0);

        assertNotEquals(calculatorHp.dumpMemory(), calculatorCasio.dumpMemory());
    }
}
