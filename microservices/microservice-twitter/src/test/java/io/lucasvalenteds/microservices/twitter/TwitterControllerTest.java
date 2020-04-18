package io.lucasvalenteds.microservices.twitter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.UserOperations;

public class TwitterControllerTest {

    private TwitterController controller;
    private Twitter twitter;
    private UserOperations userOperations;
    private TwitterProfile diego;

    @BeforeEach
    public void setUp() {
        this.twitter = mock(Twitter.class);
        this.userOperations = mock(UserOperations.class);
        this.diego = mock(TwitterProfile.class);
        this.controller = new TwitterController(twitter);
    }

    @Test
    public void testItReturnsStatusOKAndNumberOfTweetsWhenUsernameIsRegistered() {
        when(twitter.userOperations()).thenReturn(userOperations);
        when(userOperations.getUserProfile("diegopacheco")).thenReturn(diego);
        when(diego.getStatusesCount()).thenReturn(100);

        ResponseEntity<SuccessPayload> response = controller.getAmountOfTweets("diegopacheco");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100, response.getBody().getTweets());
    }

    @Test
    public void testItReturnBadRequestAndErrorMessageWhenUsernameIsNotRegistered() {
        when(twitter.userOperations()).thenReturn(userOperations);
        when(userOperations.getUserProfile("notdiegopacheco")).thenReturn(diego);
        when(diego.getStatusesCount()).thenThrow(new ResourceNotFoundException("twitter", "User not found."));

        ResponseEntity<ErrorPayload> response = controller.getAmountOfTweets("notdiegopacheco");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not found.", response.getBody().getMessage());
    }
}