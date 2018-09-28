package org.bookstore.generator.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for Generating Numbers.
 */
@RestController
@RequestMapping("/api")
@Api(description = "Generating all sorts of book numbers.")
public class NumberGeneratorResource {

    private final Logger log = LoggerFactory.getLogger(NumberGeneratorResource.class);

    /**
     * GET  /numbers : generates a number.
     *
     * @return the ResponseEntity with status 200 (OK) and the generated number
     */
    @GetMapping(path = "/numbers", produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Generates a book number.")
    public ResponseEntity<String> generateNumber() {
        log.debug("REST request to generate a number");
        String result = "BK-" + Math.random();
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/numbers/health")
    @ApiOperation(value = "Checks the health of this REST endpoint")
    public ResponseEntity<Void> health() {
        log.info("Alive and Kicking !!!");
        return ResponseEntity.ok().build();
    }
}
