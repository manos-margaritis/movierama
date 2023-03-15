package com.abandonedlabs.movierama.dto;

import com.abandonedlabs.movierama.dto.builders.UserDTOBuilder;
import com.abandonedlabs.movierama.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * The type User dto.
 */
@JsonPropertyOrder({ "id", "username", "password", "name", "email" })
@Component
public class UserDTO {

	@ApiModelProperty(example = "cfbd7d72-c325-11ed-afa1-0242ac120002", required = true)
	private UUID id;

	@NotEmpty
	@ApiModelProperty(example = "manos", required = true)
	private String username;

	@ApiModelProperty(example = "P@ssw0rd", required = true)
	private String password;

	@ApiModelProperty(example = "manos@manos.gr", required = true)
	private String email;

	@ApiModelProperty(example = "Manos Playmo")
	private String name;


	/**
	 * Instantiates a new User dto.
	 */
	public UserDTO() {
	}

	/**
	 * Instantiates a new User dto.
	 *
	 * @param id       the id
	 * @param username the username
	 * @param password the password
	 * @param email    the email
	 * @param name     the name
	 */
	public UserDTO(UUID id, String username, String password, String email, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
	}

	/**
	 * Builder user dto builder.
	 *
	 * @return the user dto builder
	 */
	public static final UserDTOBuilder builder() {
		return new UserDTOBuilder();
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
	 * Gets password.
	 *
	 * @return the password
	 */
	@ApiModelProperty(hidden = true)
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	@ApiModelProperty(hidden = true)
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
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