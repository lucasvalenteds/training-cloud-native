package io.lucasvalenteds.petstore.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/pets/search").with(PetServlet.class);
    }

    @Provides
    @Singleton
    public ObjectMapper getObjectMapperFromJackson() {
        return new ObjectMapper();
    }
}
