package io.lucasvalenteds.calculator.web;

import io.netty.handler.codec.http.HttpResponseStatus;
import netflix.karyon.health.HealthCheckHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(WebConfiguration.class)
public class HealthHandlerTest {

    @Autowired
    private HealthCheckHandler handler;

    @Test
    public void testItReturnsStatusOk() {
        assertEquals(HttpResponseStatus.OK.code(), handler.getStatus());
    }
}