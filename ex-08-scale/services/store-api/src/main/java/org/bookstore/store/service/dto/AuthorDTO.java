package org.bookstore.store.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bookstore.store.domain.enumeration.Language;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Author entity.
 */
@ApiModel( description = "Book Author" )
public class AuthorDTO implements Serializable {

    @ApiModelProperty(notes = "Technical identifier of the author. No two authors can have the same id.", required = true)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    @ApiModelProperty(notes = "Author's first name.", required = true)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    @ApiModelProperty(notes = "Author's last name.", required = true)
    private String lastName;

    @Size(max = 5000)
    @ApiModelProperty(notes = "Author's biographie.")
    private String bio;

    @ApiModelProperty(notes = "Author's date of birth.")
    private LocalDate dateOfBirth;

    @ApiModelProperty(notes = "Author's preferred language to write books.")
    private Language preferredLanguage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorDTO authorDTO = (AuthorDTO) o;
        if (authorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", bio='" + getBio() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", preferredLanguage='" + getPreferredLanguage() + "'" +
            "}";
    }
}
