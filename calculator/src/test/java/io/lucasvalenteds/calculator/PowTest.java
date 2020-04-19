package io.lucasvalenteds.calculator;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowTest {

    public static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(0.0, new Pow(0.0, 1.0)),
            Arguments.of(1.0, new Pow(1.0, 1.0)),
            Arguments.of(8.0, new Pow(2.0, 3.0)),
            Arguments.of(-2.0, new Pow(-2.0, 1.0))
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void testItWorks(double result, Pow command) {
        assertEquals(result, command.execute(), 0.01);
    }
}
