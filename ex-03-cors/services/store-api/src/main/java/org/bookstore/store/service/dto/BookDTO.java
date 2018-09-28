package org.bookstore.store.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bookstore.store.domain.enumeration.Language;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Book entity.
 */
@ApiModel( description = "Book" )
public class BookDTO implements Serializable {

    @ApiModelProperty(notes = "Technical identifier of the book. No two books can have the same id.", required = true)
    private Long id;

    @NotNull
    @Size(max = 15)
    @ApiModelProperty(notes = "Unique ISBN number of the book.", required = true)
    private String isbn;

    @NotNull
    @Size(min = 2, max = 300)
    @ApiModelProperty(notes = "Title of the book.", required = true)
    private String title;

    @Size(max = 10000)
    @ApiModelProperty(notes = "Description, abstract, comments of the book.")
    private String description;

    @DecimalMin(value = "1")
    private BigDecimal price;

    @Min(value = 1)
    @ApiModelProperty(notes = "Paper books have a number of pages.")
    private Integer nbOfPages;

    @ApiModelProperty(notes = "Date in which the book got published.")
    private LocalDate publication;

    @ApiModelProperty(notes = "Language in which the book got written.")
    private Language language;

    @ApiModelProperty(notes = "Small-size format image of the book cover.")
    private String smallImageURL;

    @ApiModelProperty(notes = "Medium-size format image of the book cover.")
    private String mediumImageURL;

    @ApiModelProperty(notes = "Technical identifier of the publisher.")
    private Long publisherId;

    @ApiModelProperty(notes = "Name of the publisher.")
    private String publisherName;

    @ApiModelProperty(notes = "Technical identifier of the book category.")
    private Long categoryId;

    @ApiModelProperty(notes = "Name of the category.")
    private String categoryName;

    @ApiModelProperty(notes = "Authors of the book.")
    private Set<AuthorDTO> authors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNbOfPages() {
        return nbOfPages;
    }

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getMediumImageURL() {
        return mediumImageURL;
    }

    public void setMediumImageURL(String mediumImageURL) {
        this.mediumImageURL = mediumImageURL;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookDTO bookDTO = (BookDTO) o;
        if (bookDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + getId() +
            ", isbn='" + getIsbn() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", nbOfPages=" + getNbOfPages() +
            ", publication='" + getPublication() + "'" +
            ", language='" + getLanguage() + "'" +
            ", smallImageURL='" + getSmallImageURL() + "'" +
            ", mediumImageURL='" + getMediumImageURL() + "'" +
            ", publisher=" + getPublisherId() +
            ", publisher='" + getPublisherName() + "'" +
            ", category=" + getCategoryId() +
            ", category='" + getCategoryName() + "'" +
            "}";
    }
}
