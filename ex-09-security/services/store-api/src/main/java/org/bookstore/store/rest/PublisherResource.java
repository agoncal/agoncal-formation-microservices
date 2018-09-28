package org.bookstore.store.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bookstore.store.rest.errors.BadRequestAlertException;
import org.bookstore.store.service.PublisherService;
import org.bookstore.store.service.dto.PublisherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Publisher.
 */
@RestController
@RequestMapping("/api")
@Api(description = "Operations on publishers.")
public class PublisherResource {

    private final Logger log = LoggerFactory.getLogger(PublisherResource.class);

    private final PublisherService publisherService;

    public PublisherResource(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * POST  /publishers : Create a new publisher.
     *
     * @param publisherDTO the publisherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new publisherDTO, or with status 400 (Bad Request) if the publisher has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(path = "/publishers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a new publisher.")
    public ResponseEntity<PublisherDTO> createPublisher(@Valid @RequestBody @ApiParam(value = "Publisher", required = true) PublisherDTO publisherDTO) throws URISyntaxException {
        log.debug("REST request to save Publisher : {}", publisherDTO);
        if (publisherDTO.getId() != null) {
            throw new BadRequestAlertException("A new publisher cannot already have an ID");
        }
        PublisherDTO result = publisherService.save(publisherDTO);
        return ResponseEntity.created(new URI("/api/publishers/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /publishers : Updates an existing publisher.
     *
     * @param publisherDTO the publisherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated publisherDTO,
     * or with status 400 (Bad Request) if the publisherDTO is not valid,
     * or with status 500 (Internal Server Error) if the publisherDTO couldn't be updated
     */
    @PutMapping(path = "/publishers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a publisher.")
    public ResponseEntity<PublisherDTO> updatePublisher(@Valid @RequestBody @ApiParam(value = "Publisher", required = true) PublisherDTO publisherDTO) {
        log.debug("REST request to update Publisher : {}", publisherDTO);
        if (publisherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        PublisherDTO result = publisherService.save(publisherDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /publishers : get all the publishers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of publishers in body
     */
    @GetMapping(path = "/publishers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all the publishers.")
    public List<PublisherDTO> getAllPublishers() {
        log.debug("REST request to get all Publishers");
        return publisherService.findAll();
    }

    /**
     * GET  /publishers/:id : get the "id" publisher.
     *
     * @param id the id of the publisherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the publisherDTO, or with status 404 (Not Found)
     */
    @GetMapping(path = "/publishers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns a publisher giving its identifier.")
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable @ApiParam(value = "Publisher identifier", required = true)Long id) {
        log.debug("REST request to get Publisher : {}", id);
        Optional<PublisherDTO> publisherDTO = publisherService.findOne(id);
        return publisherDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /publishers/:id : delete the "id" publisher.
     *
     * @param id the id of the publisherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/publishers/{id}")
    @ApiOperation(value = "Deletes a publisher giving its identifier.")
    public ResponseEntity<Void> deletePublisher(@PathVariable @ApiParam(value = "Publisher identifier", required = true) Long id) {
        log.debug("REST request to delete Publisher : {}", id);
        publisherService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/publishers/health")
    @ApiOperation(value = "Checks the health of this REST endpoint")
    public ResponseEntity<Void> health() {
        log.info("Alive and Kicking !!!");
        return ResponseEntity.ok().build();
    }
}
