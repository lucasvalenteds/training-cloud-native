package io.lucasvalenteds.microservices.github;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubController {

    private final Logger logger = LogManager.getLogger(GitHubController.class);

    private final GitHubService service;

    @Autowired
    public GitHubController(GitHubService service) {
        this.service = service;
    }

    @GetMapping("/github/{username}")
    @ResponseBody
    public ResponseEntity getAmountOfRepositories(@PathVariable("username") String username) {
        try {
            SuccessPayload payload = new SuccessPayload(service.getAmountOfRepositories(username));

            logger.info(username);
            logger.info(payload);

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(payload);
        } catch (IllegalArgumentException exception) {
            ErrorPayload payload = new ErrorPayload(exception.getMessage());

            logger.error(exception);
            logger.error(payload);

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(payload);
        }
    }
}
