package io.lucasvalenteds.microservices.twitter;

public class ErrorPayload {
    private final String message;

    public ErrorPayload(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorPayload{" +
            "message='" + message + '\'' +
            '}';
    }
}
