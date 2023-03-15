package com.abandonedlabs.movierama.dto.builders;

import com.abandonedlabs.movierama.dto.UserDTO;

import java.util.UUID;

/**
 * The type User dto builder.
 */
public class UserDTOBuilder {

    private UUID id;
    private String username;
    private String password;
    private String email;
    private String name;

    /**
     * With id user dto builder.
     *
     * @param id the id
     * @return the user dto builder
     */
    public UserDTOBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * With username user dto builder.
     *
     * @param username the username
     * @return the user dto builder
     */
    public UserDTOBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * With password user dto builder.
     *
     * @param password the password
     * @return the user dto builder
     */
    public UserDTOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * With email user dto builder.
     *
     * @param email the email
     * @return the user dto builder
     */
    public UserDTOBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * With name user dto builder.
     *
     * @param name the name
     * @return the user dto builder
     */
    public UserDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Build user dto.
     *
     * @return the user dto
     */
    public UserDTO build() {
        return new UserDTO(id, username, password, email, name);
    }
}
