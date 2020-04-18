package io.lucasvalenteds.petstore.store.jobs;

import java.util.UUID;

public interface Job {
    public UUID getPetId();

    public double getPrice();
}
