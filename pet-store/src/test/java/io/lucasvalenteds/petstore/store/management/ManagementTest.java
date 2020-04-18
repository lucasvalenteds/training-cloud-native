package io.lucasvalenteds.petstore.store.management;

import io.lucasvalenteds.petstore.store.test.CapabilityFixtures;
import io.lucasvalenteds.petstore.store.store.Capability;
import io.lucasvalenteds.petstore.store.store.PetStore;
import io.lucasvalenteds.petstore.store.store.Store;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagementTest extends CapabilityFixtures {

    private Store store;

    @BeforeEach
    public void setUp() {
        Set<Capability> capabilities = new HashSet<>();
        capabilities.add(management);
        capabilities.add(jobs);
        capabilities.add(analytics);

        store = new PetStore(capabilities);
    }

    @Test
    public void testItPersistsSomePet() {
        Optional<Management> capability = store.getCapability(Management.class);
        assertTrue(capability.isPresent());

        capability.get().registerPet(new Pet(UUID.randomUUID(), "Rex", "Labrador", 5));

        assertEquals(1, store.getKnownPets().size());
    }

    @Test
    public void testItRemovesSomePetFromStorage() {
        Optional<Management> capability = store.getCapability(Management.class);
        assertTrue(capability.isPresent());
        capability.get().registerPet(tina);

        capability.get().removePet(tina.getId());

        assertEquals(0, store.getKnownPets().size());
    }

    @Test
    public void testItDoesNothingIfIdDoesNotMatchAnyPet() {
        Optional<Management> capability = store.getCapability(Management.class);
        assertTrue(capability.isPresent());
        capability.ifPresent(it -> it.registerPet(suzy));

        capability.get().removePet(UUID.randomUUID());

        assertEquals(1, store.getKnownPets().size());
    }

    @Test
    public void testItCanFindAllPetsWithGivenAge() {
        Optional<Management> capability = store.getCapability(Management.class);
        assertTrue(capability.isPresent());
        capability.ifPresent(it -> {
            it.registerPet(tina);
            it.registerPet(amy);
            it.registerPet(suzy);
            it.registerPet(judy);
        });

        List<Pet> petsWithAgeThree = capability.get().findPetsByAge(3);

        assertEquals(2, petsWithAgeThree.size());
        assertTrue(petsWithAgeThree.contains(amy));
        assertTrue(petsWithAgeThree.contains(judy));
        assertFalse(petsWithAgeThree.contains(tina));
        assertFalse(petsWithAgeThree.contains(suzy));
    }
}
