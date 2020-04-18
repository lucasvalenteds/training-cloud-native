package io.lucasvalenteds.calculator;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyTest {

    public static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(0.0, new Multiply(0, 0.0)),
            Arguments.of(0.0, new Multiply(0.0, 1.0)),
            Arguments.of(0.0, new Multiply(1.0, 0.0)),
            Arguments.of(1.0, new Multiply(1.0, 1.0)),
            Arguments.of(6.0, new Multiply(2.0, 3.0)),
            Arguments.of(-2.0, new Multiply(-2.0, 1.0))
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void testItMultiplyAnOperandByAnother(double result, Multiply command) {
        assertEquals(result, command.execute());
    }
}
