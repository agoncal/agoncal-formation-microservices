package org.bookstore.store.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bookstore.store.rest.errors.BadRequestAlertException;
import org.bookstore.store.service.AuthorService;
import org.bookstore.store.service.dto.AuthorDTO;
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
 * REST controller for managing Author.
 */
@RestController
@RequestMapping("/api")
@Api(description = "Operations on authors.")
public class AuthorResource {

    private final Logger log = LoggerFactory.getLogger(AuthorResource.class);

    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * POST  /authors : Create a new author.
     *
     * @param authorDTO the authorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authorDTO, or with status 400 (Bad Request) if the author has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(path ="/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a new author.")
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody @ApiParam(value = "Author", required = true) AuthorDTO authorDTO) throws URISyntaxException {
        log.debug("REST request to save Author : {}", authorDTO);
        if (authorDTO.getId() != null) {
            throw new BadRequestAlertException("A new author cannot already have an ID");
        }
        AuthorDTO result = authorService.save(authorDTO);
        return ResponseEntity.created(new URI("/api/authors/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /authors : Updates an existing author.
     *
     * @param authorDTO the authorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authorDTO,
     * or with status 400 (Bad Request) if the authorDTO is not valid,
     * or with status 500 (Internal Server Error) if the authorDTO couldn't be updated
     */
    @PutMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates an author.")
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody @ApiParam(value = "Author", required = true) AuthorDTO authorDTO) {
        log.debug("REST request to update Author : {}", authorDTO);
        if (authorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        AuthorDTO result = authorService.save(authorDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /authors : get all the authors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of authors in body
     */
    @GetMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all the authors.")
    public List<AuthorDTO> getAllAuthors() {
        log.debug("REST request to get all Authors");
        return authorService.findAll();
    }

    /**
     * GET  /authors/:id : get the "id" author.
     *
     * @param id the id of the authorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authorDTO, or with status 404 (Not Found)
     */
    @GetMapping(path = "/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns an author giving its identifier.")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable @ApiParam(value = "Author identifier", required = true) Long id) {
        log.debug("REST request to get Author : {}", id);
        Optional<AuthorDTO> authorDTO = authorService.findOne(id);
        return authorDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /authors/:id : delete the "id" author.
     *
     * @param id the id of the authorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authors/{id}")
    @ApiOperation(value = "Deletes an author giving its identifier.")
    public ResponseEntity<Void> deleteAuthor(@PathVariable @ApiParam(value = "Author identifier", required = true) Long id) {
        log.debug("REST request to delete Author : {}", id);
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/authors/health")
    @ApiOperation(value = "Checks the health of this REST endpoint")
    public ResponseEntity<Void> health() {
        log.info("Alive and Kicking !!!");
        return ResponseEntity.ok().build();
    }
}
