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

public class GitHubTest {

    private RestTemplate template;
    private GitHub githubApi;

    @BeforeEach
    public void setUp() {
        template = mock(RestTemplate.class);
        githubApi = new GitHub(template);
    }

    @Test
    public void testGithubUnavailableReturnServiceUnavailableAndFriendlyMessage() {
        when(template.getForObject(any(), any(), anyString()))
            .thenThrow(HttpServerErrorException.class);

        SocialResponse response = githubApi.callService("diegopacheco");

        assertEquals("GitHub API is unavailable at the moment", response.getContent());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.httpStatus);
    }

    @Test
    public void testGithubInvalidUsernameReturnsBadRequestAndFriendlyMessage() {
        when(template.getForObject(any(), any(), anyString()))
            .thenThrow(HttpClientErrorException.class);

        SocialResponse response = githubApi.callService("notdiegopacheco");

        assertEquals("GitHub not found for notdiegopacheco", response.getContent());
        assertEquals(HttpStatus.BAD_REQUEST, response.httpStatus);
    }

    @Test
    public void testMissingGithubUsernameReturnsBadRequestWithFriendlyMessage() {
        SocialResponse response = githubApi.callService("");

        assertEquals("Missing query parameter '?github=' with username", response.getContent());
        assertEquals(HttpStatus.BAD_REQUEST, response.httpStatus);
    }
}