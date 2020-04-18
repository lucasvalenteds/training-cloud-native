package io.lucasvalenteds.calculator.web;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(WebConfiguration.class)
public class CalculateHandlerTest {

    @Autowired
    private CalculateHandler handler;
    private HttpServerRequest<ByteBuf> request = mock(HttpServerRequest.class);
    private HttpServerResponse<ByteBuf> response = mock(HttpServerResponse.class);

    @Test
    public void testItReturnsBadRequestIfOperationIsMissing() {
        Map<String, List<String>> parameter = new HashMap<>(0);
        parameter.put("a", Collections.singletonList("2.0"));
        parameter.put("b", Collections.singletonList("3.0"));
        when(request.getQueryParameters()).thenReturn(parameter);

        handler.handle(request, response);

        verify(response).setStatus(HttpResponseStatus.BAD_REQUEST);
        verify(response).writeStringAndFlush("Missing '?op=' parameter. It should be a String.");
    }

    public static Stream<Arguments> fixtureMissingOperands() {
        return Stream.of(
            Arguments.of("2.0", null),
            Arguments.of(null, "3.0")
        );
    }

    @ParameterizedTest
    @MethodSource("fixtureMissingOperands")
    public void testItReturnsBadRequestIfOneOrMoreOperandIsMissing(String operandA, String operandB) {
        Map<String, List<String>> parameter = new HashMap<>(0);
        parameter.put("op", Collections.singletonList("sum"));
        if (operandA != null) parameter.put("a", Collections.singletonList(operandA));
        if (operandB != null) parameter.put("b", Collections.singletonList(operandB));
        when(request.getQueryParameters()).thenReturn(parameter);

        handler.handle(request, response);

        verify(response).setStatus(HttpResponseStatus.BAD_REQUEST);
        verify(response).writeStringAndFlush("Missing '?a=' or '?b=' parameter. They both should be double.");
    }

    @Test
    public void testItReturnsOkIfTheOperationWasExecuted() {
        Map<String, List<String>> parameter = new HashMap<>(0);
        parameter.put("op", Collections.singletonList("pow"));
        parameter.put("a", Collections.singletonList("2.0"));
        parameter.put("b", Collections.singletonList("3.0"));
        when(request.getQueryParameters()).thenReturn(parameter);

        handler.handle(request, response);

        verify(response).setStatus(HttpResponseStatus.OK);
        verify(response).writeStringAndFlush("8.0");
    }
}