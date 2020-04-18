package io.lucasvalenteds.calculator.spring;

import io.lucasvalenteds.calculator.Calculator;
import io.lucasvalenteds.calculator.Operation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(CalculatorConfiguration.class)
public class CalculatorTest {

    @Autowired
    private Calculator calculator;

    public static Stream<Arguments> fixtures() {
        return Stream.of(
            Arguments.of("sum", 2.0, 3.0, 5.0),
            Arguments.of("sub", 2.0, 3.0, -1.0),
            Arguments.of("mul", 2.0, 3.0, 6.0),
            Arguments.of("div", 2.0, 3.0, 0.6),
            Arguments.of("pow", 2.0, 3.0, 8.0)
        );
    }

    @ParameterizedTest
    @MethodSource("fixtures")
    public void testSpringContextShouldBeUsedToExecuteCommands(
        String operation, double firstOperand, double secondOperand, double result
    ) {
        assertEquals(result, calculator.evaluate(operation, firstOperand, secondOperand), 0.1);
    }

    @Test
    public void testItReturnsNaNWhenOperationCouldNotBeEvaluated() {
        assertEquals(Double.NaN, calculator.evaluate("div", 0.0, 0.0));
    }

    @Test
    public void testItReturnsNaNWhenOperationIsNotImplemented() {
        assertEquals(Double.NaN, calculator.evaluate("not-implemented", 0.0, 0.0));
    }

    @Test
    public void testItCanExportAllOperationsDoneInThePast() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
        ArrayList<Operation> emptyMemory = new ArrayList<>(0);
        Calculator calculator = new Calculator(context, emptyMemory);

        for (int i = 0; i < 10; i++) {
            calculator.evaluate("sum", 0.0, 0.0);
            calculator.evaluate("sub", 0.0, 1.0);
            calculator.evaluate("mul", 1.0, 0.0);
            calculator.evaluate("div", 1.0, 1.0);
            calculator.evaluate("pow", 2.0, 3.0);
        }

        assertEquals(50, calculator.dumpMemory().size());
    }

    @Test
    public void testOperationsAreSavedOnMemoryInOrderOfExecution() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
        ArrayList<Operation> emptyMemory = new ArrayList<>(0);
        Calculator calculator = new Calculator(context, emptyMemory);

        List<Double> results = new ArrayList<>(0);
        for (int i = 0; i < 2; i++) {
            results.add(calculator.evaluate("sum", 0.0, 0.0));
            results.add(calculator.evaluate("sub", 0.0, 1.0));
            results.add(calculator.evaluate("mul", 1.0, 0.0));
            results.add(calculator.evaluate("div", 1.0, 1.0));
            results.add(calculator.evaluate("pow", 2.0, 3.0));
        }

        List<Double> reDoResults = calculator.dumpMemory().stream()
            .map(Operation::execute)
            .collect(Collectors.toList());

        assertEquals(results.size(), reDoResults.size());
        for (int i = 0; i < results.size(); i++) {
            assertEquals(results.get(i), reDoResults.get(i));
        }
    }
}