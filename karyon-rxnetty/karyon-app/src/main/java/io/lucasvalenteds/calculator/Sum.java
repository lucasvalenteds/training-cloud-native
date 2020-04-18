package io.lucasvalenteds.calculator;

public class Sum extends BinaryOperation {

    public Sum(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double execute() {
        return firstOperand + secondOperand;
    }
}
