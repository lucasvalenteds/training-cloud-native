package io.lucasvalenteds.microservices.social;

public class SocialPayload {

    private Object github;
    private Object twitter;

    public SocialPayload() {
    }

    public SocialPayload(Object github, Object twitter) {
        this.github = github;
        this.twitter = twitter;
    }

    public Object getGithub() {
        return github;
    }

    public void setGithub(Object github) {
        this.github = github;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    @Override
    public String toString() {
        return "SocialPayload{" +
            "github=" + github +
            ", twitter=" + twitter +
            '}';
    }
}
