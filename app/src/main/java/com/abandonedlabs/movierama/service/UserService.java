package com.abandonedlabs.movierama.service;

import com.abandonedlabs.movierama.dto.UserDTO;
import com.abandonedlabs.movierama.factory.UserFactory;
import com.abandonedlabs.movierama.model.User;
import com.abandonedlabs.movierama.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserFactory userFactory;


    /**
     * Create user user dto.
     *
     * @param userDTO the user dto
     * @return the user dto
     */
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        logger.debug("createUser({})", userDTO);
        User user;
        User userWithUsername = userRepository.findByUsername(userDTO.getUsername());

        if (userWithUsername != null) {
            throw new IllegalArgumentException("User with username '" + userDTO.getUsername() + "' already exists!");
        }
        else {
            user = userFactory.fromUserDTO(userDTO);
            setPassword(userDTO, user);
            user.setExternalId(UUID.randomUUID());

            userRepository.save(user);

            logger.info("New User added: " + user);
        }

        return userFactory.toUserDTO(user);
    }

    /**
     * Find user.
     *
     * @param externalId the external id
     * @return the user
     */
    @Transactional
    public User find(UUID externalId) {
        logger.debug("find({})", externalId);
        return userRepository.findByExternalId(externalId);
    }

    /**
     * Find user dto.
     *
     * @param username the username
     * @param password the password
     * @return the user dto
     * @throws NotFoundException the not found exception
     */
    @Transactional
    public UserDTO find(String username, String password) throws ChangeSetPersister.NotFoundException {
        logger.debug("find({}, {})", username, password);
        List<UserDTO> users = getUsers(username);

        if (users == null || users.isEmpty()) {
            throw new RuntimeException("No user found with username '" + username + "'");
        } else {
            UserDTO userDto = users.get(0);
            if (!passwordService.matches(password, userDto.getPassword())) {
                throw new AuthorizationServiceException("Invalid password '" + password + "'");
            } else {
                return userDto;
            }
        }
    }

    /**
     * Gets users.
     *
     * @param username the username
     * @return the users
     */
    @Transactional
    public List<UserDTO> getUsers(String username) {
        logger.debug("getUsers({})", username);
        List<UserDTO> userDtos;

        if (StringUtils.isNotBlank(username)) {
            User user = userRepository.findByUsername(username);
            userDtos = getUsers(user);
        } else {
            userDtos = getAll();
        }

        return userDtos;
    }

    @Transactional
    private List<UserDTO> getUsers(User user) {
        logger.debug("getUsers({})", user);
        List<UserDTO> userDtos = new ArrayList<>();

        if (user != null) {
            UserDTO userDto = userFactory.toUserDTO(user);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @Transactional
    public List<UserDTO> getAll() {
        logger.debug("getAll()");
        List<User> users = userRepository.findAll();

        List<UserDTO> userDtos = users.stream()
                .map(user -> userFactory.toUserDTO(user))
                .collect(Collectors.toList());

        return userDtos;
    }

    /**
     * Sets password.
     *
     * @param userDto the user dto
     * @param user    the user
     */
    public void setPassword(UserDTO userDto, User user) {
        String hashedPassword = passwordService.bCryptPassword(userDto.getPassword());
        user.setPassword(hashedPassword);
    }
}
