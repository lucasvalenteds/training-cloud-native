package io.lucasvalenteds.calculator;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DivisionTest {

    public static Stream<Arguments> params() {
        return Stream.of(
            Arguments.of(0.0, new Division(0.0, 1.0)),
            Arguments.of(1.0, new Division(1.0, 1.0)),
            Arguments.of(0.67, new Division(2.0, 3.0)),
            Arguments.of(-2.0, new Division(-2.0, 1.0))
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void testItDividesAndOperandByAnother(double result, Division command) {
        assertEquals(result, command.execute(), 0.01);
    }

    @Test
    public void testItThrowsErrorWhenTryingToDivideByZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Division(2.0, 0.0).execute(),
            "It's not possible to divide a number by zero"
        );
    }

}
