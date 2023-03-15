package com.abandonedlabs.movierama.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * VoteDto
 */
@JsonPropertyOrder({ "movie", "like" })
@Component
public class VoteDTO {

	@NotNull
	@ApiModelProperty(example = "cfbd7d72-c325-11ed-afa1-0242ac120002", required = true)
	private UUID movie;

	@NotNull
	@ApiModelProperty(example = "true", required = true)
	private boolean like;

	/**
	 * Instantiates a new Vote dto.
	 */
	public VoteDTO() {
	}

	/**
	 * Instantiates a new Vote dto.
	 *
	 * @param movie the movie
	 */
	public VoteDTO(UUID movie) {
		this.movie = movie;
	}

	/**
	 * Gets movie.
	 *
	 * @return the movie
	 */
	public UUID getMovie() {
		return movie;
	}

	/**
	 * Sets movie.
	 *
	 * @param movie the movie
	 */
	public void setMovie(UUID movie) {
		this.movie = movie;
	}

	/**
	 * Is like boolean.
	 *
	 * @return the boolean
	 */
	public boolean isLike() {
		return like;
	}

	/**
	 * Sets like.
	 *
	 * @param like the like
	 */
	public void setLike(boolean like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}