package io.lucasvalenteds.microservices.github;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GitHubControllerTest {

    private GitHubService service;
    private GitHubController controller;

    @BeforeEach
    public void setUp() {
        this.service = mock(GitHubService.class);
        this.controller = new GitHubController(service);
    }

    @Test
    public void testItRespondsUserAmountOfRepositories() {
        when(service.getAmountOfRepositories("john")).thenReturn(150);

        ResponseEntity<SuccessPayload> response = controller.getAmountOfRepositories("john");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(150, response.getBody().getRepositories());
    }

    @Test
    public void testItRespondsErrorWhenUsernameNotFound() {
        when(service.getAmountOfRepositories("not-a-user"))
            .thenThrow(new IllegalArgumentException());

        ResponseEntity<ErrorPayload> response = controller.getAmountOfRepositories("not-a-user");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
