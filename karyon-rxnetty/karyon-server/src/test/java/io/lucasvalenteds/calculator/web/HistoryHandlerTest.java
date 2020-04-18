package io.lucasvalenteds.calculator.web;

import io.lucasvalenteds.calculator.Calculator;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(WebConfiguration.class)
public class HistoryHandlerTest {

    @Autowired
    private Calculator calculator;

    @Autowired
    private HistoryHandler handler;

    @Test
    public void testItReturnsOkWithTheResultOfAllOperationsDoneInThePast() {
        HttpServerRequest<ByteBuf> request = mock(HttpServerRequest.class);
        HttpServerResponse<ByteBuf> response = mock(HttpServerResponse.class);
        calculator.evaluate("pow", 2.0, 3.0);
        calculator.evaluate("sum", 4.0, 6.0);

        handler.handle(request, response);

        verify(response).setStatus(HttpResponseStatus.OK);
        verify(response).writeStringAndFlush(contains("8.0"));
        verify(response).writeStringAndFlush(contains("10.0"));
    }
}