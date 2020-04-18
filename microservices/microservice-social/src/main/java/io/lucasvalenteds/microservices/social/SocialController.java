package io.lucasvalenteds.microservices.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lucasvalenteds.microservices.social.integration.GitHub;
import io.lucasvalenteds.microservices.social.integration.Twitter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialController {

    private static final Logger logger = LogManager.getLogger(SocialController.class);

    private final ObjectMapper objectMapper;
    private final Twitter twitterService;
    private final GitHub githubService;

    @Autowired
    public SocialController(
        ObjectMapper objectMapper,
        @Qualifier("twitter") Twitter twitterService,
        @Qualifier("github") GitHub githubService
    ) {
        this.objectMapper = objectMapper;
        this.twitterService = twitterService;
        this.githubService = githubService;
    }

    @GetMapping(value = "/social", produces = "application/json")
    @ResponseBody
    public ResponseEntity getSocialMediaData(
        @RequestParam(value = "twitter", required = false, defaultValue = "") String usernameTwitter,
        @RequestParam(value = "github", required = false, defaultValue = "") String usernameGithub
    ) throws IOException {
        SocialResponse twitterResponse = twitterService.callService(usernameTwitter);
        SocialResponse githubResponse = githubService.callService(usernameGithub);

        List<HttpStatus> status = new ArrayList<>(0);
        status.add(twitterResponse.httpStatus);
        status.add(githubResponse.httpStatus);

        return ResponseEntity
            .status(
                status.stream()
                    .distinct()
                    .max(Comparator.comparingInt(HttpStatus::value))
                    .orElse(HttpStatus.INTERNAL_SERVER_ERROR)
            )
            .body(objectMapper.writeValueAsString(new SocialPayload(
                githubResponse.content,
                twitterResponse.content
            )));
    }
}
