package io.lucasvalenteds.calculator.web;

import io.netty.handler.codec.http.HttpResponseStatus;
import netflix.karyon.health.HealthCheckHandler;

public class HealthHandler implements HealthCheckHandler {
    @Override
    public int getStatus() {
        return HttpResponseStatus.OK.code();
    }
}
