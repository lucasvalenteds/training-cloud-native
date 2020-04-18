package io.lucasvalenteds.calculator.web;

public class ResponseSuccess {
    private final double result;

    public ResponseSuccess(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }
}
