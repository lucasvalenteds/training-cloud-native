package io.lucasvalenteds.calculator.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.calculator.spring.CalculatorConfiguration;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig({CalculatorConfiguration.class, CalculatorWebConfiguration.class})
public class CalculatorServletTest {

    @Autowired
    private ObjectMapper objectMapperJson;

    @Autowired
    private CalculatorServlet servlet;

    @Test
    public void testItReturnsTheCalculationWhenOperationAndBothOperandsAreValid() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("op")).thenReturn("sum");
        when(request.getParameter("a")).thenReturn("2.0");
        when(request.getParameter("b")).thenReturn("3.0");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getParameter("op");
        verify(request, atLeastOnce()).getParameter("a");
        verify(request, atLeastOnce()).getParameter("b");
        printWriter.flush();
        assertEquals(objectMapperJson.writeValueAsString(new ResponseSuccess(5.0)), stringWriter.toString());
    }

    @Test
    public void testItReturnsErrorWhenOperationIsMissing() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("op")).thenReturn(null);
        when(request.getParameter("a")).thenReturn("2.0");
        when(request.getParameter("b")).thenReturn("3.0");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);
        printWriter.flush();

        verify(request, atLeastOnce()).getParameter("op");
        verify(request, atLeastOnce()).getParameter("a");
        verify(request, atLeastOnce()).getParameter("b");
        assertEquals(objectMapperJson.writeValueAsString(new ResponseError(
            "Missing operation. It should be passed in the 'op' parameter."
        )), stringWriter.toString());
    }

    public static Stream<Arguments> fixturesMissingOperands() {
        return Stream.of(
            Arguments.of("2.0", null),
            Arguments.of(null, "3.0")
        );
    }

    @ParameterizedTest
    @MethodSource("fixturesMissingOperands")
    public void testItReturnsErrorWhenAnyOperandIsMissing(String operandA, String operandB) throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("op")).thenReturn("sum");
        when(request.getParameter("a")).thenReturn(operandA);
        when(request.getParameter("b")).thenReturn(operandB);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);
        printWriter.flush();

        verify(request, atLeastOnce()).getParameter("op");
        verify(request, atLeastOnce()).getParameter("a");
        verify(request, atLeastOnce()).getParameter("b");
        assertEquals(objectMapperJson.writeValueAsString(new ResponseError(
            "Missing one or more operands. Two double operands must be informed."
        )), stringWriter.toString());
    }

    public static Stream<Arguments> fixturesInvalidOperands() {
        return Stream.of(
            Arguments.of("2.0", "foo"),
            Arguments.of("bar", "3.0")
        );
    }

    @ParameterizedTest
    @MethodSource("fixturesInvalidOperands")
    public void testItReturnsErrorWhenAnyOperandIsInvalid(String operandA, String operandB) throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("op")).thenReturn("sum");
        when(request.getParameter("a")).thenReturn(operandA);
        when(request.getParameter("b")).thenReturn(operandB);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);
        printWriter.flush();

        verify(request, atLeastOnce()).getParameter("op");
        verify(request, atLeastOnce()).getParameter("a");
        verify(request, atLeastOnce()).getParameter("b");
        assertEquals(objectMapperJson.writeValueAsString(new ResponseError(
            "Invalid pair of operands. They must be both double values."
        )), stringWriter.toString());
    }
}