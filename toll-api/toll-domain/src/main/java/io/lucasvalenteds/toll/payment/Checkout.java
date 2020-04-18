package io.lucasvalenteds.toll.payment;

public class Checkout {
    private final double received;
    private final double change;

    public Checkout(double received, double change) {
        this.received = received;
        this.change = change;
    }

    public double getReceived() {
        return received;
    }

    public double getChange() {
        return change;
    }
}
