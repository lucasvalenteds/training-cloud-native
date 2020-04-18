package io.lucasvalenteds.microservices.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@PropertySource("classpath:twitter.properties")
public class TwitterConfiguration {

    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Bean
    public Twitter twitter() {
        return new TwitterTemplate(consumerKey, consumerSecret);
    }
}
