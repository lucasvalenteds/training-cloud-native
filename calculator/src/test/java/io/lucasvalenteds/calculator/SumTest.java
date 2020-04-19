package io.lucasvalenteds.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumTest {

    public static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(0.0, new Sum(0, 0.0)),
            Arguments.of(1.0, new Sum(0.0, 1.0)),
            Arguments.of(1.0, new Sum(0.0, 1.0)),
            Arguments.of(2.0, new Sum(1.0, 1.0)),
            Arguments.of(5.0, new Sum(2.0, 3.0)),
            Arguments.of(-1.0, new Sum(-2.0, 1.0))
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void testItSumsAnOperandWithAnother(double result, Sum command) {
        assertEquals(result, command.execute());
    }
}