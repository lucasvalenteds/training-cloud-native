package io.lucasvalenteds.calculator.spring;

import io.lucasvalenteds.calculator.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CalculatorConfiguration.class);
        context.refresh();

        Calculator calculator = context.getBean(Calculator.class);
        System.out.println(calculator.evaluate("sum", 2.0, 2.0));
        System.out.println(calculator.evaluate("sub", 5.0, 10.0));
        System.out.println(calculator.evaluate("mul", 3.0, 5.0));
        System.out.println(calculator.evaluate("div", 4.0, 2.0));
        System.out.println(calculator.evaluate("pow", 2.0, 3.0));

        System.out.println(calculator.dumpMemory().size() + " operations executed.");
    }
}
