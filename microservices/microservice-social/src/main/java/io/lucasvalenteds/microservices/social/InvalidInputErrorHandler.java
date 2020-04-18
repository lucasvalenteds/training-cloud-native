package io.lucasvalenteds.microservices.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class InvalidInputErrorHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Autowired
    public InvalidInputErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new IllegalArgumentException(objectMapper.readTree(response.getBody()).get("message").textValue());
    }
}
