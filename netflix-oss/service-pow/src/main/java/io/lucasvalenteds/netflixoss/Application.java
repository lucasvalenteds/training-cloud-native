package io.lucasvalenteds.netflixoss;

import io.netty.buffer.ByteBuf;
import netflix.karyon.Karyon;
import netflix.karyon.KaryonBootstrapModule;
import netflix.karyon.KaryonServer;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrapModule;
import netflix.karyon.eureka.KaryonEurekaModule;
import netflix.karyon.transport.http.HttpRequestHandler;
import netflix.karyon.transport.http.SimpleUriRouter;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;

public class Application {

    public static void main(String[] args) {
        HealthHandler healthCheckHandler = new HealthHandler();

        SimpleUriRouter<ByteBuf, ByteBuf> router = new SimpleUriRouter<>();
        router.addUri("/healthcheck", new HealthCheckEndpoint(healthCheckHandler));
        router.addUri("/pow", new PowHandler());

        KaryonServer server = Karyon.forRequestHandler(8081,
            new HttpRequestHandler<>(router),
            new KaryonBootstrapModule(healthCheckHandler),
            new ArchaiusBootstrapModule("service-pow"),
            ShutdownModule.asBootstrapModule(),
            KaryonEurekaModule.asBootstrapModule()
        );

        server.startAndWaitTillShutdown();
    }
}
