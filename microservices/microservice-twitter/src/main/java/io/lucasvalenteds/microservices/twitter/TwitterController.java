package io.lucasvalenteds.microservices.twitter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwitterController {

    private final Logger logger = LogManager.getLogger(TwitterController.class);

    private final Twitter twitter;

    @Autowired
    public TwitterController(Twitter twitter) {
        this.twitter = twitter;
    }

    @GetMapping("/twitter/{username}")
    @ResponseBody
    public ResponseEntity getAmountOfTweets(@PathVariable("username") String username) {
        try {
            SuccessPayload payload = new SuccessPayload(
                twitter.userOperations()
                    .getUserProfile(username)
                    .getStatusesCount()
            );

            logger.info(username);
            logger.info(payload);

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(payload);
        } catch (ResourceNotFoundException exception) {
            ErrorPayload payload = new ErrorPayload(exception.getMessage());

            logger.error(exception);
            logger.error(payload);

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(payload);
        }
    }
}
