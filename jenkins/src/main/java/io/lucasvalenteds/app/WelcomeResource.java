package io.lucasvalenteds.app;

import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/welcome")
public class WelcomeResource {

    @GET
    public String getMessage(@QueryParam("name") String name) {
        return Optional.ofNullable(name)
            .map(it -> "Hello World, " + it + "!")
            .orElse("Hello World, stranger!");
    }
}
