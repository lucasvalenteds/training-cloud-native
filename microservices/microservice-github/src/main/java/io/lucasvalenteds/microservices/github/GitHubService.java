package io.lucasvalenteds.microservices.github;

public interface GitHubService {

    public int getAmountOfRepositories(String username) throws IllegalArgumentException;
}
