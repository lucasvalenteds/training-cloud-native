package io.lucasvalenteds.microservices.twitter;

public class SuccessPayload {
    private final int tweets;

    public SuccessPayload(int tweets) {
        this.tweets = tweets;
    }

    public int getTweets() {
        return tweets;
    }

    @Override
    public String toString() {
        return "SuccessPayload{" +
            "tweets=" + tweets +
            '}';
    }
}
