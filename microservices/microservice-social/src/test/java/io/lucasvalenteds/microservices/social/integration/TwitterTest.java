package io.lucasvalenteds.microservices.social.integration;

import io.lucasvalenteds.microservices.social.SocialResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class TwitterTest {

    private RestTemplate template;
    private Twitter twitterApi;

    @BeforeEach
    public void setUp() {
        template = mock(RestTemplate.class);
        twitterApi = new Twitter(template);
    }

    @Test
    public void testTwitterUnavailableReturnServiceUnavailableAndFriendlyMessage() {
        when(template.getForObject(any(), any(), anyString()))
            .thenThrow(HttpServerErrorException.class);

        SocialResponse response = twitterApi.callService("diegopacheco");

        assertEquals("Twitter API is unavailable at the moment", response.getContent());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.httpStatus);
    }

    @Test
    public void testTwitterInvalidUsernameReturnsBadRequestAndFriendlyMessage() {
        when(template.getForObject(any(), any(), anyString()))
            .thenThrow(HttpClientErrorException.class);

        SocialResponse response = twitterApi.callService("notdiegopacheco");

        assertEquals("Twitter not found for notdiegopacheco", response.getContent());
        assertEquals(HttpStatus.BAD_REQUEST, response.httpStatus);
    }

    @Test
    public void testMissingTwitterUsernameReturnsBadRequestWithFriendlyMessage() {
        SocialResponse response = twitterApi.callService("");

        assertEquals("Missing query parameter '?twitter=' with username", response.getContent());
        assertEquals(HttpStatus.BAD_REQUEST, response.httpStatus);
    }

}