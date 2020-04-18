package io.lucasvalenteds.petstore.store.store;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import io.lucasvalenteds.petstore.store.analytics.Analytics;
import io.lucasvalenteds.petstore.store.jobs.Jobs;
import io.lucasvalenteds.petstore.store.management.Management;

import java.util.Set;

public class StoreModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder.newSetBinder(this.binder(), Capability.class).addBinding().to(Analytics.class);
        Multibinder.newSetBinder(this.binder(), Capability.class).addBinding().to(Jobs.class);
        Multibinder.newSetBinder(this.binder(), Capability.class).addBinding().to(Management.class);
    }

    @Provides
    @Singleton
    public Store store(Set<Capability> capabilities) {
        return new PetStore(capabilities);
    }

    @Provides
    @Singleton
    @Named("analytics")
    public Capability analytics() {
        return new Analytics();
    }

    @Provides
    @Singleton
    @Named("jobs")
    public Capability jobs() {
        return new Jobs();
    }

    @Provides
    @Singleton
    @Named("management")
    public Capability management() {
        return new Management();
    }
}
