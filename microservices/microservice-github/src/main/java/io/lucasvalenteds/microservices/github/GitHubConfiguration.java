package io.lucasvalenteds.microservices.github;

import java.io.IOException;
import org.kohsuke.github.GitHub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitHubConfiguration {

    @Bean
    public GitHub client() throws IOException {
        return GitHub.connectAnonymously();
    }

    @Bean
    public GitHubService service(GitHub client) {
        return new GitHubServiceDefault(client);
    }
}
