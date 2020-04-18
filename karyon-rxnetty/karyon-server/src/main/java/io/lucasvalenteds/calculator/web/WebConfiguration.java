package io.lucasvalenteds.calculator.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.calculator.Calculator;
import io.lucasvalenteds.calculator.spring.CalculatorConfiguration;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.Karyon;
import netflix.karyon.KaryonBootstrapModule;
import netflix.karyon.KaryonServer;
import netflix.karyon.health.HealthCheckHandler;
import netflix.karyon.transport.http.HttpRequestHandler;
import netflix.karyon.transport.http.SimpleUriRouter;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CalculatorConfiguration.class)
public class WebConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("calculate")
    public CalculateHandler calculateHandler(Calculator calculator) {
        return new CalculateHandler(calculator);
    }

    @Bean("history")
    public HistoryHandler historyHandler(Calculator calculator) {
        return new HistoryHandler(calculator);
    }

    @Bean("notFound")
    public NotFoundHandler notFoundHandler() {
        return new NotFoundHandler();
    }

    @Bean("health")
    public HealthCheckHandler healthCheckHandler() {
        return new HealthHandler();
    }

    @Bean("router")
    public SimpleUriRouter<ByteBuf, ByteBuf> router(Calculator calculator) {
        SimpleUriRouter<ByteBuf, ByteBuf> router = new SimpleUriRouter<>();

        router.addUri("/healthcheck", new HealthCheckEndpoint(healthCheckHandler()));
        router.addUri("/calculate", calculateHandler(calculator));
        router.addUri("/history", historyHandler(calculator));
        router.addUri("*", notFoundHandler());

        return router;
    }

    @Bean("server")
    public KaryonServer server(SimpleUriRouter<ByteBuf, ByteBuf> router, Calculator calculator) {
        return Karyon.forRequestHandler(8081,
            new HttpRequestHandler<>(router),
            new KaryonBootstrapModule(healthCheckHandler())
        );
    }
}
