package io.lucasvalenteds.petstore.store.store;

import io.lucasvalenteds.petstore.store.jobs.Job;
import io.lucasvalenteds.petstore.store.management.Pet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;

public class PetStore implements Store {

    private final List<Job> history = new ArrayList<>();
    private final List<Pet> knownPets = new ArrayList<>();
    private final Set<Capability> capabilities = new HashSet<>();

    @Inject
    public PetStore(Set<Capability> capabilities) {
        Optional.ofNullable(capabilities).ifPresent(this.capabilities::addAll);
        this.capabilities.forEach(capability -> capability.providedTo(this));
    }

    @Override
    public List<Job> getJobsDone() {
        return this.history;
    }

    @Override
    public List<Pet> getKnownPets() {
        return this.knownPets;
    }

    public <T extends Capability> Optional<T> getCapability(Class<T> type) {
        return capabilities.stream()
            .filter(type::isInstance)
            .map(it -> (T) it)
            .findFirst();
    }
}
