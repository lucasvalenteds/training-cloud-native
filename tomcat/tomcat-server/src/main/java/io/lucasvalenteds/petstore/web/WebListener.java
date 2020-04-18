package io.lucasvalenteds.petstore.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import io.lucasvalenteds.petstore.store.management.Management;
import io.lucasvalenteds.petstore.store.management.Pet;
import io.lucasvalenteds.petstore.store.store.Store;
import io.lucasvalenteds.petstore.store.store.StoreModule;
import java.util.UUID;

public class WebListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        Injector injector = Guice.createInjector(
            new StoreModule(),
            new WebModule()
        );

        injector.getInstance(Store.class).getCapability(Management.class).ifPresent(it -> {
            it.registerPet(new Pet(UUID.randomUUID(), "Rex", "Labrador", 3));
            it.registerPet(new Pet(UUID.randomUUID(), "Tina", "Beagle", 3));
            it.registerPet(new Pet(UUID.randomUUID(), "Suzy", "Poodle", 1));
        });

        return injector;
    }
}
