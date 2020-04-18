package io.lucasvalenteds.toll.board;

public class PriceBoardItem {
    private final String vehicleType;
    private final double baseFee;
    private final double extraFee;

    public PriceBoardItem(String vehicleType, double baseFee, double extraFee) {
        this.vehicleType = vehicleType;
        this.baseFee = baseFee;
        this.extraFee = extraFee;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public double getExtraFee() {
        return extraFee;
    }
}
