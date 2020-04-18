package io.lucasvalenteds.netflixoss;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import rx.Observable;

public class PowHandler implements RequestHandler<ByteBuf, ByteBuf> {

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        Double a = request.getQueryParameters().get("a").stream()
            .findFirst()
            .map(Double::valueOf)
            .orElse(Double.NaN);

        Double b = request.getQueryParameters().get("b").stream()
            .findFirst()
            .map(Double::valueOf)
            .orElse(Double.NaN);

        response.setStatus(HttpResponseStatus.OK);
        return response.writeStringAndFlush("" + Math.pow(a, b));
    }
}
