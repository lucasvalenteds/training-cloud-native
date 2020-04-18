package io.lucasvalenteds.microservices.github;

import java.io.IOException;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;

@Service
public class GitHubServiceDefault implements GitHubService {

    private final GitHub client;

    public GitHubServiceDefault(GitHub client) {
        this.client = client;
    }

    @Override
    public int getAmountOfRepositories(String username) {
        try {
            return client.getUser(username)
                .getRepositories()
                .size();
        } catch (IOException exception) {
            throw new IllegalArgumentException("Username '" + username + "' not found");
        }
    }
}
