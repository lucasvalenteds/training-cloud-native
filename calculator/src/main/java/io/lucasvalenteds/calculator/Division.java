package io.lucasvalenteds.calculator;

public class Division extends BinaryOperation {

    public Division(double firstOperand, double secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double execute() {
        if (secondOperand == 0.0) {
            throw new IllegalArgumentException("It's not possible to divide a number by zero");
        } else {
            return firstOperand / secondOperand;
        }
    }
}
