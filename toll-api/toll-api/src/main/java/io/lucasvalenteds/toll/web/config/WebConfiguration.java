package io.lucasvalenteds.toll.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.lucasvalenteds.toll.web.board.PriceBoardResource;
import io.lucasvalenteds.toll.web.server.TollApplication;
import io.lucasvalenteds.toll.web.toll.TollResource;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class WebConfiguration {

    @Bean
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    @DependsOn("cxf")
    public Server server(Application application, TollResource tollResource, PriceBoardResource priceBoardResource) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance()
            .createEndpoint(application, JAXRSServerFactoryBean.class);
        factory.setServiceBean(tollResource);
        factory.setServiceBean(priceBoardResource);
        factory.setAddress(factory.getAddress());
        return factory.create();
    }

    @Bean
    public ObjectMapper jsonObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean
    public Application application() {
        return new TollApplication();
    }

    @Bean
    public TollResource tollResource() {
        return new TollResource();
    }

    @Bean
    public PriceBoardResource priceBoardResource() {
        return new PriceBoardResource();
    }
}
