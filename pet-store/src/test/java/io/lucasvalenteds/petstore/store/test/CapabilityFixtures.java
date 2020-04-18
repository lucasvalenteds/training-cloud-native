package io.lucasvalenteds.petstore.store.test;

import io.lucasvalenteds.petstore.store.analytics.Analytics;
import io.lucasvalenteds.petstore.store.jobs.Jobs;
import io.lucasvalenteds.petstore.store.management.Management;
import io.lucasvalenteds.petstore.store.management.Pet;
import io.lucasvalenteds.petstore.store.store.Capability;

import java.util.UUID;

public class CapabilityFixtures {

    protected final Pet tina = new Pet(UUID.randomUUID(), "Tina", "Basset", 5);
    protected final Pet amy = new Pet(UUID.randomUUID(), "Amy", "Shih Tzu", 3);
    protected final Pet suzy = new Pet(UUID.randomUUID(), "Suzy", "Pug", 7);
    protected final Pet judy = new Pet(UUID.randomUUID(), "Judy", "Yorkshire", 3);

    protected final Capability management = new Management();
    protected final Capability jobs = new Jobs();
    protected final Capability analytics = new Analytics();
}
