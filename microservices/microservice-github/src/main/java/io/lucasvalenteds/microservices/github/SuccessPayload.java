package io.lucasvalenteds.microservices.github;

public class SuccessPayload {
    private final int repositories;

    public SuccessPayload(int repositories) {
        this.repositories = repositories;
    }

    public int getRepositories() {
        return repositories;
    }

    @Override
    public String toString() {
        return "SuccessPayload{" +
            "repositories=" + repositories +
            '}';
    }
}
