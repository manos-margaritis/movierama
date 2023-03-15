package com.abandonedlabs.movierama.dto;

import com.abandonedlabs.movierama.dto.builders.MovieDTOBuilder;
import com.abandonedlabs.movierama.dto.builders.UserDTOBuilder;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * MovieDto
 */
@JsonPropertyOrder({ "id", "title", "description", "publisher", "publicationDate", "likes", "hates" })
public class MovieDTO {
	@NotEmpty
	@ApiModelProperty(example = "Interstellar", required = true)
	private String title;

	@ApiModelProperty(example = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.")
	private String description;

	@ApiModelProperty(example = "cfbd7d72-c325-11ed-afa1-0242ac120002")
	private UUID id;

	private UserDTO publisher;

	private ZonedDateTime publicationDate;

	private Long likes;

	private Long hates;

	/**
	 * Instantiates a new Movie dto.
	 */
	public MovieDTO() {
	}

	/**
	 * Instantiates a new Movie dto.
	 *
	 * @param title           the title
	 * @param description     the description
	 * @param id              the id
	 * @param publisher       the publisher
	 * @param publicationDate the publication date
	 * @param likes           the likes
	 * @param hates           the hates
	 */
	public MovieDTO(String title, String description, UUID id, UserDTO publisher, ZonedDateTime publicationDate, Long likes, Long hates) {
		this.title = title;
		this.description = description;
		this.id = id;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.likes = likes;
		this.hates = hates;
	}

	/**
	 * Builder movie dto builder.
	 *
	 * @return the movie dto builder
	 */
	public static final MovieDTOBuilder builder() {
		return new MovieDTOBuilder();
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets publisher.
	 *
	 * @return the publisher
	 */
	public UserDTO getPublisher() {
		return publisher;
	}

	/**
	 * Sets publisher.
	 *
	 * @param publisher the publisher
	 */
	public void setPublisher(UserDTO publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets publication date.
	 *
	 * @return the publication date
	 */
	public ZonedDateTime getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Sets publication date.
	 *
	 * @param publicationDate the publication date
	 */
	public void setPublicationDate(ZonedDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * Gets likes.
	 *
	 * @return the likes
	 */
	public Long getLikes() {
		return likes;
	}

	/**
	 * Sets likes.
	 *
	 * @param likes the likes
	 */
	public void setLikes(Long likes) {
		this.likes = likes;
	}

	/**
	 * Gets hates.
	 *
	 * @return the hates
	 */
	public Long getHates() {
		return hates;
	}

	/**
	 * Sets hates.
	 *
	 * @param hates the hates
	 */
	public void setHates(Long hates) {
		this.hates = hates;
	}

	/**
	 * Gets title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
