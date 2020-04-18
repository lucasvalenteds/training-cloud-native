package io.lucasvalenteds.microservices.social;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.microservices.social.integration.GitHub;
import io.lucasvalenteds.microservices.social.integration.Twitter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SocialControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Twitter twitterApi;
    private GitHub githubApi;
    private SocialController controller;

    @BeforeEach
    public void setUp() {
        twitterApi = mock(Twitter.class);
        githubApi = mock(GitHub.class);
        controller = new SocialController(objectMapper, twitterApi, githubApi);
    }

    @Test
    public void testBothServicesReturningOK() throws IOException {
        when(githubApi.callService("diegopacheco")).thenReturn(new SocialResponse(
            objectMapper.readTree("{\"repositories\":338}"),
            HttpStatus.OK
        ));
        when(twitterApi.callService("diego_pacheco")).thenReturn(new SocialResponse(
            objectMapper.readTree("{\"tweets\":400}"),
            HttpStatus.OK
        ));

        ResponseEntity response = controller.getSocialMediaData("diego_pacheco", "diegopacheco");
        assertNotNull(response.getBody());

        JsonNode payload = objectMapper.readTree(response.getBody().toString());
        assertTrue(payload.has("github"));
        assertTrue(payload.has("twitter"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}