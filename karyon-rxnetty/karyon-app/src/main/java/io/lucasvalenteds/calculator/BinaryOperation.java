package io.lucasvalenteds.calculator;

public abstract class BinaryOperation implements Operation {

    protected final double firstOperand;
    protected final double secondOperand;

    public BinaryOperation(double firstOperand, double secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }
}
