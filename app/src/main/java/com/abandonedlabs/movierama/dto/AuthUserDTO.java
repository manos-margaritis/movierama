package com.abandonedlabs.movierama.dto;

import com.abandonedlabs.movierama.dto.builders.UserDTOBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The type Auth user dto.
 */
@JsonPropertyOrder({ "username", "token", "id" })
@Component
public class AuthUserDTO {

	private String username;

	private String token;

	private UUID id;

	/**
	 * Instantiates a new Auth user dto.
	 */
	public AuthUserDTO() {
	}

	/**
	 * Instantiates a new Auth user dto.
	 *
	 * @param username the username
	 * @param token    the token
	 * @param id       the id
	 */
	public AuthUserDTO(String username, String token, UUID id) {
		this.username = username;
		this.token = token;
		this.id = id;
	}

	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets token.
	 *
	 * @param token the token
	 */
	public void setToken(String token) {
		this.token = token;
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
}