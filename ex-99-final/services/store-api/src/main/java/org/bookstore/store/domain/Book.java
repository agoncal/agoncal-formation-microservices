package org.bookstore.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bookstore.store.domain.enumeration.Language;

import javax.persistence.*;
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
 * A Book.
 */
@Entity
@Table(name = "str_book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "isbn", length = 15, nullable = false)
    private String isbn;

    @NotNull
    @Size(min = 2, max = 300)
    @Column(name = "title", length = 300, nullable = false)
    private String title;

    @Size(max = 10000)
    @Column(name = "description", length = 10000)
    private String description;

    @DecimalMin(value = "1")
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Min(value = 1)
    @Column(name = "nb_of_pages")
    private Integer nbOfPages;

    @Column(name = "publication")
    private LocalDate publication;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Column(name = "small_image_url")
    private String smallImageURL;

    @Column(name = "medium_image_url")
    private String mediumImageURL;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Publisher publisher;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Category category;

    @ManyToMany
    @JoinTable(name = "book_authors",
               joinColumns = @JoinColumn(name = "books_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "authors_id", referencedColumnName = "id"))
    private Set<Author> authors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Book description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Book price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNbOfPages() {
        return nbOfPages;
    }

    public Book nbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
        return this;
    }

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public Book publication(LocalDate publication) {
        this.publication = publication;
        return this;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public Language getLanguage() {
        return language;
    }

    public Book language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public Book smallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
        return this;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getMediumImageURL() {
        return mediumImageURL;
    }

    public Book mediumImageURL(String mediumImageURL) {
        this.mediumImageURL = mediumImageURL;
        return this;
    }

    public void setMediumImageURL(String mediumImageURL) {
        this.mediumImageURL = mediumImageURL;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Book publisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public Book category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Book authors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    public Book addAuthors(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
        return this;
    }

    public Book removeAuthors(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
        return this;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (book.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Book{" +
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
            "}";
    }
}
