package org.bookstore.store.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bookstore.store.rest.errors.BadRequestAlertException;
import org.bookstore.store.service.CategoryService;
import org.bookstore.store.service.dto.CategoryDTO;
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
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
@Api(description = "Operations on categories.")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * POST  /categories : Create a new category.
     *
     * @param categoryDTO the categoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryDTO, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a new category.")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody @ApiParam(value = "Category", required = true) CategoryDTO categoryDTO) throws URISyntaxException {
        log.debug("REST request to save Category : {}", categoryDTO);
        if (categoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /categories : Updates an existing category.
     *
     * @param categoryDTO the categoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryDTO,
     * or with status 400 (Bad Request) if the categoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the categoryDTO couldn't be updated
     */
    @PutMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a category.")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody @ApiParam(value = "Category", required = true) CategoryDTO categoryDTO) {
        log.debug("REST request to update Category : {}", categoryDTO);
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in body
     */
    @GetMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns all the categories.")
    public List<CategoryDTO> getAllCategories() {
        log.debug("REST request to get all Categories");
        return categoryService.findAll();
    }

    /**
     * GET  /categories/:id : get the "id" category.
     *
     * @param id the id of the categoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoryDTO, or with status 404 (Not Found)
     */
    @GetMapping(path = "/categories/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Returns a category giving its identifier.")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable @ApiParam(value = "Category identifier", required = true) Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
        return categoryDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /categories/:id : delete the "id" category.
     *
     * @param id the id of the categoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/categories/{id}")
    @ApiOperation(value = "Deletes a category giving its identifier.")
    public ResponseEntity<Void> deleteCategory(@PathVariable @ApiParam(value = "Category identifier", required = true) Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categories/health")
    @ApiOperation(value = "Checks the health of this REST endpoint")
    public ResponseEntity<Void> health() {
        log.info("Alive and Kicking !!!");
        return ResponseEntity.ok().build();
    }
}
