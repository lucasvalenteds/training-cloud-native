package io.lucasvalenteds.petstore.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.lucasvalenteds.petstore.store.management.Management;
import io.lucasvalenteds.petstore.store.management.Pet;
import io.lucasvalenteds.petstore.store.store.Store;
import io.lucasvalenteds.petstore.store.store.StoreModule;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PetServletTest {

    private final List<Pet> pets = new ArrayList<>(0);
    private ObjectMapper objectMapperJson;
    private PetServlet servlet;

    @BeforeEach
    public void setUp() {
        this.pets.add(new Pet(UUID.randomUUID(), "Rex", "Labrador", 3));
        this.pets.add(new Pet(UUID.randomUUID(), "Tina", "Beagle", 3));

        Injector injector = Guice.createInjector(
            new StoreModule(),
            new WebModule()
        );

        injector.getInstance(Store.class).getCapability(Management.class)
            .ifPresent(it -> this.pets.forEach(it::registerPet));

        this.objectMapperJson = injector.getInstance(ObjectMapper.class);
        this.servlet = injector.getInstance(PetServlet.class);
    }

    @Test
    void testItReturnsListOfPetsWhenValidAgeIsInformed() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("age")).thenReturn("3");

        StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getParameter("age");
        printWriter.flush();
        assertEquals(stringWriter.toString(), objectMapperJson.writeValueAsString(pets));
    }

    @Test
    void testItReturnEmptyListWhenNoPetsWithDesiredAgeExists() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("age")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getParameter("age");
        printWriter.flush();
        assertEquals(stringWriter.toString(), objectMapperJson.writeValueAsString(new ArrayList<Pet>(0)));
    }

    @Test
    void testItReturnErrorMessageWhenInvalidAgeIsInformed() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("age")).thenReturn("notAnIntegerAge");

        StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getParameter("age");
        printWriter.flush();
        assertEquals(stringWriter.toString(), objectMapperJson.writeValueAsString(new ErrorResponse(
            "Invalid 'age' parameter. It must be an integer."
        )));
    }

    @Test
    void testItReturnErrorMessageWhenAgeIsNotInformed() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("age")).thenReturn(null);

        StringWriter stringWriter = new StringWriter();
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        verify(request, atLeastOnce()).getParameter("age");
        printWriter.flush();
        assertEquals(stringWriter.toString(), objectMapperJson.writeValueAsString(new ErrorResponse(
            "Missing 'age' parameter. It must be an integer."
        )));
    }

}