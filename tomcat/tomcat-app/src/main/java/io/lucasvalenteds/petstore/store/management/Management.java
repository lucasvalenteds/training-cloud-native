package io.lucasvalenteds.petstore.store.management;

import io.lucasvalenteds.petstore.store.store.Capability;
import io.lucasvalenteds.petstore.store.store.Store;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Management implements Capability {

    private Store store;

    @Override
    public void providedTo(Store store) {
        this.store = store;
    }

    public void registerPet(Pet pet) {
        store.getKnownPets().add(pet);
    }

    public void removePet(UUID petId) {
        store.getKnownPets().removeIf(pet -> pet.getId().equals(petId));
    }

    public List<Pet> findPetsByAge(int age) {
        return store.getKnownPets().stream()
            .filter(pet -> pet.getAge() == age)
            .collect(Collectors.toList());
    }
}
