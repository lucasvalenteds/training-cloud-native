package io.lucasvalenteds.toll.vehicle;


public class Truck implements Vehicle {
    private int numberOfAxles;

    public Truck() {
        this.numberOfAxles = 0;
    }

    public Truck(int numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }

    public int getNumberOfAxles() {
        return numberOfAxles;
    }

    public void setNumberOfAxles(int numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }

    @Override
    public double getPrice() {
        return 3.95;
    }

    @Override
    public double getExtra() {
        return 0.5;
    }
}
