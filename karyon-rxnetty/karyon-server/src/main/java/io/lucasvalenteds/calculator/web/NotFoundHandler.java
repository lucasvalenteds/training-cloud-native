package io.lucasvalenteds.calculator.web;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import rx.Observable;

public class NotFoundHandler implements RequestHandler<ByteBuf, ByteBuf> {

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        response.setStatus(HttpResponseStatus.NOT_FOUND);
        return response.writeStringAndFlush("Endpoint " + request.getPath() + " not found");
    }
}
