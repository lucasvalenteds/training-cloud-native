package io.lucasvalenteds.calculator;

public class Multiply extends BinaryOperation {

    public Multiply(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double execute() {
        return firstOperand * secondOperand;
    }
}
