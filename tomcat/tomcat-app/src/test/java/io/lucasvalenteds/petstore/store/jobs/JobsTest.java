package io.lucasvalenteds.petstore.store.jobs;

import io.lucasvalenteds.petstore.store.test.CapabilityFixtures;
import io.lucasvalenteds.petstore.store.store.Capability;
import io.lucasvalenteds.petstore.store.store.PetStore;
import io.lucasvalenteds.petstore.store.store.Store;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JobsTest extends CapabilityFixtures {

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
    public void testItDoesBathAccordingToCustomerOptions() {
        Optional<Jobs> capability = store.getCapability(Jobs.class);
        assertTrue(capability.isPresent());

        capability.get().doBath(tina.getId(), Bath.Type.WATER, Bath.Smell.WITH_PERFUME);
        capability.get().doBath(tina.getId(), Bath.Type.WATER, Bath.Smell.WITHOUT_PERFUME);

        assertEquals(2, store.getJobsDone().size());
    }

    @Test
    public void testItDoesHaircutAccordingToCustomerOptions() {
        Optional<Jobs> capability = store.getCapability(Jobs.class);
        assertTrue(capability.isPresent());

        capability.get().doHaircut(tina.getId(), Haircut.Type.LONG);
        capability.get().doHaircut(tina.getId(), Haircut.Type.SHORT);

        assertEquals(2, store.getJobsDone().size());
    }
}