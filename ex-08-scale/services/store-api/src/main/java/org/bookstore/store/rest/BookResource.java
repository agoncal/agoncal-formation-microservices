package org.bookstore.store.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bookstore.store.rest.errors.BadRequestAlertException;
import org.bookstore.store.service.BookService;
import org.bookstore.store.service.dto.BookDTO;
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
 * REST controller for managing Book.
 */
@RestController
@RequestMapping("/api")
@Api(description = "Operations on books.")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * POST  /books : Create a new book.
     *
     * @param bookDTO the bookDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bookDTO, or with status 400 (Bad Request) if the book has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a new book.")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody @ApiParam(value = "Book", required = true) BookDTO bookDTO) throws URISyntaxException {
        log.debug("REST request to save Book : {}", bookDTO);
        if (bookDTO.getId() != null) {
            throw new BadRequestAlertException("A new book cannot already have an ID");
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.created(new URI("/api/books/" + result.getId()))
                .body(result);
    }

    /**
     * PUT  /books : Updates an existing book.
     *
     * @param bookDTO the bookDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bookDTO,
     * or with status 400 (Bad Request) if the bookDTO is not valid,
     * or with status 500 (Internal Server Error) if the bookDTO couldn't be updated
     */
    @PutMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a book.")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody @ApiParam(value = "Book", required = true) BookDTO bookDTO) {
        log.debug("REST request to update Book : {}", bookDTO);
        if (bookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * GET  /books : get all the books.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of books in body
     */
    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all the books.")
    public List<BookDTO> getAllBooks() {
        log.debug("REST request to get all Books");
        return bookService.findAll();
    }

    /**
     * GET  /books/:id : get the "id" book.
     *
     * @param id the id of the bookDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bookDTO, or with status 404 (Not Found)
     */
    @GetMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns a book giving its identifier.")
    public ResponseEntity<BookDTO> getBook(@PathVariable @ApiParam(value = "Book identifier", required = true) Long id) {
        log.debug("REST request to get Book : {}", id);
        Optional<BookDTO> bookDTO = bookService.findOne(id);
        return bookDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /books/:id : delete the "id" book.
     *
     * @param id the id of the bookDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/books/{id}")
    @ApiOperation(value = "Deletes a book giving its identifier.")
    public ResponseEntity<Void> deleteBook(@PathVariable @ApiParam(value = "Book identifier", required = true) Long id) {
        log.debug("REST request to delete Book : {}", id);
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * SEARCH  /books/search?query=:query : search for the book corresponding
     * to the query.
     *
     * @param query the query of the book search
     * @return the result of the search
     */
    @GetMapping(path = "/books/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all the books that follow a search criteria.")
    public List<BookDTO> searchBooks(@RequestParam @ApiParam(value = "Search criteria") String query) {
        log.debug("REST request to search Books for query {}", query);
        return bookService.search(query);
    }

    @GetMapping("/books/health")
    @ApiOperation(value = "Checks the health of this REST endpoint")
    public ResponseEntity<Void> health() {
        log.info("Alive and Kicking !!!");
        return ResponseEntity.ok().build();
    }
}
