package io.lucasvalenteds.app;

import java.net.URI;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
    public static void main(String[] args) {
        JdkHttpServerFactory.createHttpServer(
            URI.create("http://localhost:8080/"),
            ResourceConfig.forApplicationClass(WelcomeApplication.class)
        );
    }
}
