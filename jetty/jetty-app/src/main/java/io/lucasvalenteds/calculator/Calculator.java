package io.lucasvalenteds.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class Calculator {

    private final List<Operation> memory;
    private final ApplicationContext context;

    public Calculator(ApplicationContext context, List<Operation> memory) {
        this.context = context;
        this.memory = new ArrayList<>(0);
        Optional.ofNullable(memory).ifPresent(this.memory::addAll);
    }

    public double evaluate(String operationCode, double firstOperand, double secondOperand) {
        try {
            Operation operation = (Operation) context.getBean(operationCode, firstOperand, secondOperand);

            memory.add(operation);

            return operation.execute();
        } catch (BeansException | IllegalArgumentException exception) {
            return Double.NaN;
        }
    }

    public List<Operation> dumpMemory() {
        return this.memory;
    }
}
