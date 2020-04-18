package io.lucasvalenteds.calculator.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.calculator.Calculator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/evaluate")
public class CalculatorServlet extends HttpServlet {

    @Autowired
    private ObjectMapper objectMapperJson;

    @Autowired
    private Calculator calculator;

    @Override
    public void init(ServletConfig config) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {
            Optional<String> possibleOperation = Optional.ofNullable(request.getParameter("op"));
            Optional<String> possibleOperandA = Optional.ofNullable(request.getParameter("a"));
            Optional<String> possibleOperandB = Optional.ofNullable(request.getParameter("b"));

            if (!possibleOperation.isPresent()) {
                writer.write(objectMapperJson.writeValueAsString(new ResponseError(
                    "Missing operation. It should be passed in the 'op' parameter."
                )));
                return;
            }

            if (!possibleOperandA.isPresent() || !possibleOperandB.isPresent()) {
                writer.write(objectMapperJson.writeValueAsString(new ResponseError(
                    "Missing one or more operands. Two double operands must be informed."
                )));
                return;
            }

            try {
                writer.write(objectMapperJson.writeValueAsString(new ResponseSuccess(
                    calculator.evaluate(
                        possibleOperation.get(),
                        possibleOperandA.map(Double::new).get(),
                        possibleOperandB.map(Double::new).get()
                    ))
                ));
            } catch (NumberFormatException exception) {
                writer.write(objectMapperJson.writeValueAsString(new ResponseError(
                    "Invalid pair of operands. They must be both double values."
                )));
            }
        } catch (IOException exception) {
            System.out.println("Exception CalculatorServlet.doGet(): " + exception.getMessage());
        }
    }
}
