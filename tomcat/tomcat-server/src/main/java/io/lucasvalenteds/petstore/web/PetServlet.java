package io.lucasvalenteds.petstore.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.petstore.store.management.Management;
import io.lucasvalenteds.petstore.store.store.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class PetServlet extends HttpServlet {

    private ObjectMapper objectMapperJson;
    private Store store;

    @Inject
    public PetServlet(ObjectMapper objectMapperJson, Store store) {
        this.objectMapperJson = objectMapperJson;
        this.store = store;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter writer = response.getWriter()) {
            Optional<Management> management = store.getCapability(Management.class);
            if (!management.isPresent()) {
                writer.write(objectMapperJson.writeValueAsString(new ErrorResponse(
                    "Internal error related to dependency injection."
                )));
                return;
            }

            Optional<String> possibleAge = Optional.ofNullable(request.getParameter("age"));

            if (possibleAge.isPresent()) {
                try {
                    int age = possibleAge.map(Integer::new).get();

                    writer.write(objectMapperJson.writeValueAsString(management.get().findPetsByAge(age)));
                } catch (NumberFormatException exception) {
                    writer.write(objectMapperJson.writeValueAsString(new ErrorResponse(
                        "Invalid 'age' parameter. It must be an integer."
                    )));
                }
            } else {
                writer.write(objectMapperJson.writeValueAsString(new ErrorResponse(
                    "Missing 'age' parameter. It must be an integer."
                )));
            }
        } catch (IOException exception) {
            System.out.println("Error getting the writer: " + exception.getMessage());
        }
    }
}
