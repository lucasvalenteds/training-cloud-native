package io.lucasvalenteds.petstore.store.jobs;

import io.lucasvalenteds.petstore.store.store.Capability;
import io.lucasvalenteds.petstore.store.store.Store;
import java.util.UUID;

public class Jobs implements Capability {

    private Store store;

    @Override
    public void providedTo(Store store) {
        this.store = store;
    }

    public void doBath(UUID petId, Bath.Type type, Bath.Smell smell) {
        store.getJobsDone().add(new Bath(petId, type, smell));
    }

    public void doHaircut(UUID petId, Haircut.Type type) {
        store.getJobsDone().add(new Haircut(petId, type));
    }
}
