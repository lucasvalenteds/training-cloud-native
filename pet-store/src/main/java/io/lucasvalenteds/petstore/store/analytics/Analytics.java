package io.lucasvalenteds.petstore.store.analytics;

import io.lucasvalenteds.petstore.store.jobs.Job;
import io.lucasvalenteds.petstore.store.management.Pet;
import io.lucasvalenteds.petstore.store.store.Capability;
import io.lucasvalenteds.petstore.store.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Analytics implements Capability {

    private Store store;

    @Override
    public void providedTo(Store store) {
        this.store = store;
    }

    public List<PetRevenue> getTopPetsByRevenue(int numberOfResults) {
        return store.getKnownPets().stream()
            .map(this::calculatePetRevenue)
            .sorted(Comparator.comparing(PetRevenue::getRevenue).reversed())
            .limit(numberOfResults)
            .collect(Collectors.toList());
    }

    private PetRevenue calculatePetRevenue(Pet pet) {
        double totalSpentByThisPet = store.getJobsDone().stream()
            .filter(service -> service.getPetId().equals(pet.getId()))
            .mapToDouble(Job::getPrice)
            .sum();

        return new PetRevenue(pet, totalSpentByThisPet);
    }
}
