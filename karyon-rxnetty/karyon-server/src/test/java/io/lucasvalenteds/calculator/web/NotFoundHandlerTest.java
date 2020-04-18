package io.lucasvalenteds.calculator.web;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(WebConfiguration.class)
public class NotFoundHandlerTest {

    @Autowired
    private NotFoundHandler handler;

    @Test
    public void testItReturnsNotFoundAndMessageWithEndpointPath() {
        HttpServerRequest<ByteBuf> request = mock(HttpServerRequest.class);
        HttpServerResponse<ByteBuf> response = mock(HttpServerResponse.class);
        when(request.getPath()).thenReturn("/foo");

        handler.handle(request, response);

        verify(response).setStatus(HttpResponseStatus.NOT_FOUND);
        verify(response).writeStringAndFlush("Endpoint /foo not found");
    }
}