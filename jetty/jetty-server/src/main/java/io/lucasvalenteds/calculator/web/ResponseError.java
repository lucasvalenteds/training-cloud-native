package io.lucasvalenteds.calculator.web;

public class ResponseError {
    private final String message;

    public ResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
