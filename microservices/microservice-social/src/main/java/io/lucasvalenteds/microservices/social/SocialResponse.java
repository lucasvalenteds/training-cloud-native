package io.lucasvalenteds.microservices.social;

import org.springframework.http.HttpStatus;

public class SocialResponse {

    public Object content;
    public HttpStatus httpStatus;

    public SocialResponse() {
    }

    public SocialResponse(Object content, HttpStatus httpStatus) {
        this.content = content;
        this.httpStatus = httpStatus;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
