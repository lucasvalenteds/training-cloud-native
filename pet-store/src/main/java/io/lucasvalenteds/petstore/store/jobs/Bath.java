package io.lucasvalenteds.petstore.store.jobs;

import java.util.UUID;

public class Bath implements Job {

    public enum Type {
        DRY, WATER
    }

    public enum Smell {
        WITH_PERFUME, WITHOUT_PERFUME
    }

    private final UUID petId;
    private final Bath.Type type;
    private final Bath.Smell smell;

    public Bath(UUID petId, Bath.Type type, Bath.Smell smell) {
        this.petId = petId;
        this.type = type;
        this.smell = smell;
    }

    @Override
    public UUID getPetId() {
        return petId;
    }

    @Override
    public double getPrice() {
        return 15.00;
    }

    @Override
    public String toString() {
        return "Bath{" +
            "petId=" + petId +
            ", type=" + type +
            '}';
    }
}
