package com.abandonedlabs.movierama.repository;

import com.abandonedlabs.movierama.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Find by username and password user.
     *
     * @param username the username
     * @param password the password
     * @return the user
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail(String email);

    /**
     * Find by external id user.
     *
     * @param externalId the external id
     * @return the user
     */
    User findByExternalId(UUID externalId);
}