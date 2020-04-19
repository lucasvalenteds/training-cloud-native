package io.lucasvalenteds.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionTest {

    public static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(0.0, new Subtraction(0.0, 0.0)),
            Arguments.of(-1.0, new Subtraction(0.0, 1.0)),
            Arguments.of(1.0, new Subtraction(1.0, 0.0)),
            Arguments.of(0.0, new Subtraction(1.0, 1.0)),
            Arguments.of(-1.0, new Subtraction(2.0, 3.0)),
            Arguments.of(-3.0, new Subtraction(-2.0, 1.0))
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void testItDividesAnOperandByAnother(double result, Operation operation) {
        assertEquals(result, operation.execute());
    }

}