package io.lucasvalenteds.microservices.social.integration;

import io.lucasvalenteds.microservices.social.SocialResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class GitHub {

    private final Logger logger = LogManager.getLogger(Twitter.class);
    private final RestTemplate githubApi;

    public GitHub(RestTemplate githubApi) {
        this.githubApi = githubApi;
    }

    public SocialResponse callService(String github) {
        SocialResponse response = new SocialResponse();

        if (github.isEmpty()) {
            response.setContent("Missing query parameter '?github=' with username");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            return response;
        }

        try {
            response.setContent(githubApi.getForObject("/github/{username}", Object.class, github));
            response.setHttpStatus(HttpStatus.OK);
            logger.info("GitHub found for " + github);
        } catch (IllegalArgumentException | HttpClientErrorException exception) {
            response.setContent("GitHub not found for " + github);
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            logger.warn("GitHub not found for " + github);
        } catch (Exception exception) {
            response.setContent("GitHub API is unavailable at the moment");
            response.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
            logger.error("GitHub API unavailable for " + github);
        }

        return response;
    }
}
