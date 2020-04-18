package io.lucasvalenteds.calculator;

public class Subtraction extends BinaryOperation {

    public Subtraction(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double execute() {
        return firstOperand - secondOperand;
    }
}
