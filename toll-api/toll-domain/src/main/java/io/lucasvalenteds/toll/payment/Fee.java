package io.lucasvalenteds.toll.payment;

public interface Fee {
    default public double getPrice() {
        return 0.0;
    }

    default public double getExtra() {
        return 0.0;
    }
}
