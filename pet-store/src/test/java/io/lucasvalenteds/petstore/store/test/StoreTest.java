package io.lucasvalenteds.petstore.store.test;

import com.google.inject.Guice;
import io.lucasvalenteds.petstore.store.analytics.Analytics;
import io.lucasvalenteds.petstore.store.jobs.Bath;
import io.lucasvalenteds.petstore.store.jobs.Haircut;
import io.lucasvalenteds.petstore.store.jobs.Jobs;
import io.lucasvalenteds.petstore.store.management.Management;
import io.lucasvalenteds.petstore.store.management.Pet;
import io.lucasvalenteds.petstore.store.analytics.PetRevenue;
import io.lucasvalenteds.petstore.store.store.Store;
import io.lucasvalenteds.petstore.store.store.StoreModule;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StoreTest {

    private final Pet tina = new Pet(UUID.randomUUID(), "Tina", "Basset", 5);

    private Store store;
    private Analytics analytics;
    private Jobs jobs;
    private Management management;

    @BeforeEach
    public void setUp() {
        store = Guice.createInjector(new StoreModule()).getInstance(Store.class);
        analytics = store.getCapability(Analytics.class).get();
        jobs = store.getCapability(Jobs.class).get();
        management = store.getCapability(Management.class).get();
    }

    @Test
    public void testNewCustomerWorkflow() {
        addTinaToPetsList();
        doHerOneHaircut();
        doHerOneBath();
        calculateRevenueAfterHerJobs();
        findHerByAge();
    }

    private void addTinaToPetsList() {
        management.registerPet(tina);

        assertEquals(1, store.getKnownPets().size());
    }

    private void doHerOneHaircut() {
        jobs.doHaircut(tina.getId(), Haircut.Type.SHORT);

        assertEquals(1, store.getJobsDone().size());
    }

    private void doHerOneBath() {
        jobs.doBath(tina.getId(), Bath.Type.WATER, Bath.Smell.WITH_PERFUME);

        assertEquals(2, store.getJobsDone().size());
    }

    private void calculateRevenueAfterHerJobs() {
        List<PetRevenue> revenues = analytics.getTopPetsByRevenue(1);
        assertEquals(1, revenues.size());

        PetRevenue revenue = revenues.get(0);
        Assertions.assertEquals(tina, revenue.getPet());
        assertEquals(40.0, revenue.getRevenue());
    }

    private void findHerByAge() {
        List<Pet> petsByAge = management.findPetsByAge(tina.getAge());
        assertEquals(1, petsByAge.size());
        assertTrue(petsByAge.contains(tina));
    }
}
