package io.lucasvalenteds.toll.web;

import io.lucasvalenteds.toll.web.config.DomainConfiguration;
import io.lucasvalenteds.toll.web.config.ServerConfiguration;
import io.lucasvalenteds.toll.web.config.WebConfiguration;
import org.eclipse.jetty.server.Server;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(DomainConfiguration.class);
        context.register(WebConfiguration.class);
        context.register(ServerConfiguration.class);
        context.refresh();

        Server jettyServer = context.getBean(Server.class);
        jettyServer.start();
        jettyServer.join();
    }
}
