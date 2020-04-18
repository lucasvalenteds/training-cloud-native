package io.lucasvalenteds.calculator.web;

import io.lucasvalenteds.calculator.Calculator;
import io.lucasvalenteds.calculator.Operation;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import java.util.stream.Collectors;
import rx.Observable;

public class HistoryHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private final Calculator calculator;

    public HistoryHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        response.setStatus(HttpResponseStatus.OK);
        return response.writeStringAndFlush(
            calculator.dumpMemory().stream()
                .map(Operation::execute)
                .map(String::valueOf)
                .collect(Collectors.joining("\n"))
        );
    }
}
