package io.lucasvalenteds.petstore.store.analytics;

import io.lucasvalenteds.petstore.store.test.CapabilityFixtures;
import io.lucasvalenteds.petstore.store.jobs.Bath;
import io.lucasvalenteds.petstore.store.jobs.Haircut;
import io.lucasvalenteds.petstore.store.jobs.Jobs;
import io.lucasvalenteds.petstore.store.management.Management;
import io.lucasvalenteds.petstore.store.store.Capability;
import io.lucasvalenteds.petstore.store.store.PetStore;
import io.lucasvalenteds.petstore.store.store.Store;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnalyticsTest extends CapabilityFixtures {

    private Store store;
    private final Analytics analytics = new Analytics();

    @BeforeEach
    public void setUp() {
        Set<Capability> capabilities = new HashSet<>();
        capabilities.add(management);
        capabilities.add(jobs);
        capabilities.add(analytics);
        store = new PetStore(capabilities);

        store.getCapability(Management.class).ifPresent(it -> {
            it.registerPet(tina);
            it.registerPet(amy);
            it.registerPet(suzy);
            it.registerPet(judy);
        });

        store.getCapability(Jobs.class).ifPresent(it -> {
            it.doBath(tina.getId(), Bath.Type.WATER, Bath.Smell.WITH_PERFUME);
            it.doBath(tina.getId(), Bath.Type.WATER, Bath.Smell.WITH_PERFUME);
            it.doHaircut(tina.getId(), Haircut.Type.LONG);
            it.doBath(amy.getId(), Bath.Type.WATER, Bath.Smell.WITH_PERFUME);
            it.doBath(amy.getId(), Bath.Type.WATER, Bath.Smell.WITH_PERFUME);
            it.doHaircut(suzy.getId(), Haircut.Type.LONG);
            it.doBath(judy.getId(), Bath.Type.WATER, Bath.Smell.WITH_PERFUME);
        });
    }

    @Test
    public void testItProvidesTopTenPetsByRevenue() {
        int numberOfResults = 2;
        List<PetRevenue> topTenPets = analytics.getTopPetsByRevenue(numberOfResults);

        assertEquals(numberOfResults, topTenPets.size());
        assertTrue(topTenPets.get(0).getRevenue() >= topTenPets.get(1).getRevenue());
        assertEquals(tina, topTenPets.get(0).getPet());
        assertEquals(amy, topTenPets.get(1).getPet());
    }
}
