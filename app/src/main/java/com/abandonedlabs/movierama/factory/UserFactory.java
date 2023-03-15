package com.abandonedlabs.movierama.factory;

import com.abandonedlabs.movierama.dto.UserDTO;
import com.abandonedlabs.movierama.model.User;
import org.springframework.stereotype.Component;

/**
 * The type User factory.
 */
@Component
public class UserFactory {

    /**
     * To user dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .withId(user.getExternalId())
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .withUsername(user.getUsername())
                .build();
    }

    /**
     * From user dto user.
     *
     * @param userDTO the user dto
     * @return the user
     */
    public User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setExternalId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        return user;
    }

}
