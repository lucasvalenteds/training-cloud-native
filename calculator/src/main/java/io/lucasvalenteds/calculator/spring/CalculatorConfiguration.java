package io.lucasvalenteds.calculator.spring;

import io.lucasvalenteds.calculator.Calculator;
import io.lucasvalenteds.calculator.Operation;
import io.lucasvalenteds.calculator.Division;
import io.lucasvalenteds.calculator.Multiply;
import io.lucasvalenteds.calculator.Pow;
import io.lucasvalenteds.calculator.Subtraction;
import io.lucasvalenteds.calculator.Sum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CalculatorConfiguration {

    @Bean("memory")
    public List<Operation> memory() {
        return new ArrayList<>(0);
    }

    @Bean("calculator")
    public Calculator calculator(ApplicationContext context, @Qualifier("memory") List<Operation> memory) {
        return new Calculator(context, memory);
    }

    @Bean("sum")
    @Scope("prototype")
    public Operation sum(double firstOperand, double secondOperand) {
        return new Sum(firstOperand, secondOperand);
    }

    @Bean("sub")
    @Scope("prototype")
    public Operation sub(double firstOperand, double secondOperand) {
        return new Subtraction(firstOperand, secondOperand);
    }

    @Bean("mul")
    @Scope("prototype")
    public Operation mul(double firstOperand, double secondOperand) {
        return new Multiply(firstOperand, secondOperand);
    }

    @Bean("div")
    @Scope("prototype")
    public Operation div(double firstOperand, double secondOperand) {
        return new Division(firstOperand, secondOperand);
    }

    @Bean("pow")
    @Scope("prototype")
    public Operation pow(double firstOperand, double secondOperand) {
        return new Pow(firstOperand, secondOperand);
    }
}
