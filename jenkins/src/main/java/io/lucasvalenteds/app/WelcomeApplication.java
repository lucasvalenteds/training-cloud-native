package io.lucasvalenteds.app;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@ApplicationPath("/")
@Consumes(MediaType.TEXT_PLAIN)
public class WelcomeApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>(0);

        resources.add(WelcomeResource.class);

        return resources;
    }
}
