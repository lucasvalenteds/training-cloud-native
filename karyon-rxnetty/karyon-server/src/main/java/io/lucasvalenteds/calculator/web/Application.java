package io.lucasvalenteds.calculator.web;

import netflix.karyon.KaryonServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebConfiguration.class);
        context.refresh();

        KaryonServer server = context.getBean(KaryonServer.class);
        server.startAndWaitTillShutdown();
    }
}
