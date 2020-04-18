package io.lucasvalenteds.petstore.store.analytics;

import io.lucasvalenteds.petstore.store.management.Pet;

public class PetRevenue {
    private final Pet pet;
    private final double revenue;

    public PetRevenue(Pet pet, double revenue) {
        this.pet = pet;
        this.revenue = revenue;
    }

    public Pet getPet() {
        return pet;
    }

    public double getRevenue() {
        return revenue;
    }
}
