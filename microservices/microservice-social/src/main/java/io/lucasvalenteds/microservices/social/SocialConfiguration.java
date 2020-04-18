package io.lucasvalenteds.microservices.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.microservices.social.integration.GitHub;
import io.lucasvalenteds.microservices.social.integration.Twitter;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SocialConfiguration {

    @Value("${service.github.url}")
    private String githubUrl;

    @Value("${service.twitter.url}")
    private String twitterUrl;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Qualifier("twitterApi")
    @Scope("prototype")
    public RestTemplate githubApi(ObjectMapper objectMapper) {
        return new RestTemplateBuilder()
            .rootUri(githubUrl)
            .setConnectTimeout(Duration.ofSeconds(10))
            .setReadTimeout(Duration.ofSeconds(10))
            .errorHandler(new InvalidInputErrorHandler(objectMapper))
            .build();
    }

    @Bean
    @Qualifier("twitterApi")
    @Scope("prototype")
    public RestTemplate twitterApi(ObjectMapper objectMapper) {
        return new RestTemplateBuilder()
            .rootUri(twitterUrl)
            .setConnectTimeout(Duration.ofSeconds(10))
            .setReadTimeout(Duration.ofSeconds(10))
            .errorHandler(new InvalidInputErrorHandler(objectMapper))
            .build();
    }

    @Bean
    @Qualifier("twitter")
    public Twitter twitter(@Qualifier("twitterApi") RestTemplate twitterApi) {
        return new Twitter(twitterApi);
    }

    @Bean
    @Qualifier("github")
    public GitHub github(@Qualifier("githubApi") RestTemplate githubApi) {
        return new GitHub(githubApi);
    }
}
