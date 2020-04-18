package io.lucasvalenteds.toll.web.config;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ServerConfiguration {

    @Bean
    public Server jettyServer(ApplicationContext applicationContext) {
        Server server = new Server(8081);

        ServletHolder servletHolder = new ServletHolder(new CXFServlet());
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/*");

        ContextHandler.Context servletContext = contextHandler.getServletContext();
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
        server.setHandler(contextHandler);

        return server;
    }
}
