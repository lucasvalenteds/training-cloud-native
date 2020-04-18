package io.lucasvalenteds.petstore.store.store;

import io.lucasvalenteds.petstore.store.jobs.Job;
import io.lucasvalenteds.petstore.store.management.Pet;
import java.util.List;
import java.util.Optional;

public interface Store {

    public List<Job> getJobsDone();

    public List<Pet> getKnownPets();

    public <T extends Capability> Optional<T> getCapability(Class<T> type);
}
