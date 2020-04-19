package io.lucasvalenteds.calculator;

public class Pow extends BinaryOperation {

    public Pow(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double execute() {
        return Math.pow(firstOperand, secondOperand);
    }
}
