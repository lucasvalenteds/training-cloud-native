package io.lucasvalenteds.petstore.store.management;

import java.util.UUID;

public class Pet {
    private final UUID id;
    private final String name;
    private final String breed;
    private final int age;

    public Pet(UUID id, String name, String breed, int age) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pet{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", breed='" + breed + '\'' +
            ", age=" + age +
            '}';
    }
}
