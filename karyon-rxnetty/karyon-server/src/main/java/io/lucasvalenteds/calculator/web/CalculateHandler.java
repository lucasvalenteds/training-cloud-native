package io.lucasvalenteds.calculator.web;

import io.lucasvalenteds.calculator.Calculator;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import rx.Observable;

public class CalculateHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private final Calculator calculator;

    public CalculateHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        Set<Map.Entry<String, List<String>>> parameters = request.getQueryParameters().entrySet();
        Optional<String> possibleOperation = getOperationParameter(parameters);
        OptionalDouble possibleOperandA = getOperandParameter("a", parameters);
        OptionalDouble possibleOperandB = getOperandParameter("b", parameters);

        if (!possibleOperation.isPresent()) {
            response.setStatus(HttpResponseStatus.BAD_REQUEST);
            return response.writeStringAndFlush("Missing '?op=' parameter. It should be a String.");
        }

        if (!possibleOperandA.isPresent() || !possibleOperandB.isPresent()) {
            response.setStatus(HttpResponseStatus.BAD_REQUEST);
            return response.writeStringAndFlush("Missing '?a=' or '?b=' parameter. They both should be double.");
        }

        response.setStatus(HttpResponseStatus.OK);
        return response.writeStringAndFlush(String.valueOf(calculator.evaluate(
            possibleOperation.get(),
            possibleOperandA.getAsDouble(),
            possibleOperandB.getAsDouble()
        )));
    }

    private OptionalDouble getOperandParameter(String parameter, Set<Map.Entry<String, List<String>>> parameters) {
        return parameters.stream()
            .filter(it -> it.getKey().equals(parameter))
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .mapToDouble(Double::valueOf)
            .findFirst();
    }

    private Optional<String> getOperationParameter(Set<Map.Entry<String, List<String>>> parameters) {
        return parameters.stream()
            .filter(it -> it.getKey().equals("op"))
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .filter(it -> !it.isEmpty())
            .findFirst();
    }
}
