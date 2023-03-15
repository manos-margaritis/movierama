package com.abandonedlabs.movierama.dto.builders;

import com.abandonedlabs.movierama.dto.MovieDTO;
import com.abandonedlabs.movierama.dto.UserDTO;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * The type Movie dto builder.
 */
public class MovieDTOBuilder {

    private UUID id;
    private String title;
    private String description;
    private UserDTO publisher;
    private ZonedDateTime publicationDate;
    private Long likes;
    private Long hates;

    /**
     * With id movie dto builder.
     *
     * @param id the id
     * @return the movie dto builder
     */
    public MovieDTOBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * With title movie dto builder.
     *
     * @param title the title
     * @return the movie dto builder
     */
    public MovieDTOBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * With description movie dto builder.
     *
     * @param description the description
     * @return the movie dto builder
     */
    public MovieDTOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * With publisher movie dto builder.
     *
     * @param publisher the publisher
     * @return the movie dto builder
     */
    public MovieDTOBuilder withPublisher(UserDTO publisher) {
        this.publisher = publisher;
        return this;
    }

    /**
     * With publication date movie dto builder.
     *
     * @param publicationDate the publication date
     * @return the movie dto builder
     */
    public MovieDTOBuilder withPublicationDate(ZonedDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    /**
     * With likes movie dto builder.
     *
     * @param likes the likes
     * @return the movie dto builder
     */
    public MovieDTOBuilder withLikes(Long likes) {
        this.likes = likes;
        return this;
    }

    /**
     * With hates movie dto builder.
     *
     * @param hates the hates
     * @return the movie dto builder
     */
    public MovieDTOBuilder withHates(Long hates) {
        this.hates = hates;
        return this;
    }

    /**
     * Build movie dto.
     *
     * @return the movie dto
     */
    public MovieDTO build() {
        return new MovieDTO(title, description,id, publisher, publicationDate, likes, hates);
    }
}
