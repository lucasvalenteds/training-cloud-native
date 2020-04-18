package io.lucasvalenteds.petstore.store.jobs;

import java.util.UUID;

public class Haircut implements Job {

    public enum Type {
        SHORT, LONG
    }

    private final UUID petId;
    private final Type type;

    public Haircut(UUID petId, Type type) {
        this.petId = petId;
        this.type = type;
    }

    @Override
    public UUID getPetId() {
        return petId;
    }

    @Override
    public double getPrice() {
        return 25.00;
    }

    @Override
    public String toString() {
        return "Haircut{" +
            "petId=" + petId +
            ", type=" + type +
            '}';
    }
}
