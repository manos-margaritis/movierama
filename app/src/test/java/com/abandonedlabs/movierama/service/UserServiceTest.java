package com.abandonedlabs.movierama.service;

import com.abandonedlabs.movierama.dto.UserDTO;
import com.abandonedlabs.movierama.factory.UserFactory;
import com.abandonedlabs.movierama.model.User;
import com.abandonedlabs.movierama.repository.MovieRepository;
import com.abandonedlabs.movierama.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * The type User service test.
 */
public class UserServiceTest {
    /**
     * The Logger.
     */
    @Mock
    Logger logger;
    /**
     * The User repository.
     */
    @Mock
    UserRepository userRepository;
    /**
     * The Movie repository.
     */
    @Mock
    MovieRepository movieRepository;
    /**
     * The Hash service.
     */
    @Mock
    PasswordService passwordService;
    /**
     * The User factory.
     */
    @Mock
    UserFactory userFactory;
    /**
     * The User service.
     */
    @InjectMocks
    UserService userService;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test create.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCreate() throws Exception {
        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(userFactory.toUserDTO(any())).thenReturn(new UserDTO(null, "username", null, null, null));
        when(userFactory.fromUserDTO(any())).thenReturn(new User());

        UserDTO result = userService.createUser(new UserDTO(null, "username", null, null, null));
        Assert.assertEquals(new UserDTO(null, "username", null, null, null), result);
    }

    /**
     * Test find.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFind() throws Exception {
        when(userRepository.findByExternalId(any())).thenReturn(new User());

        User result = userService.find(null);
        Assert.assertEquals(new User(), result);
    }

    /**
     * Test find 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testFind2() throws Exception {
        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(passwordService.matches(anyString(), anyString())).thenReturn(true);
        when(userFactory.toUserDTO(any())).thenReturn(new UserDTO(null, null, "password", null, null));

        UserDTO result = userService.find("username", "password");
        Assert.assertEquals(new UserDTO(null, null, "password", null, null), result);
    }

    /**
     * Test list 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void testList2() throws Exception {
        when(userRepository.findByUsername(anyString())).thenReturn(new User());
        when(userFactory.toUserDTO(any())).thenReturn(new UserDTO(null, "username", "password", "email", "name"));

        List<UserDTO> result = userService.getUsers("username");
        Assert.assertEquals(List.of(new UserDTO(null, "username", "password", "email", "name")), result);
    }

    /**
     * Test list 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void testList3() throws Exception {
        when(userFactory.toUserDTO(any())).thenReturn(new UserDTO(null, "username", "password", "email", "name"));

        List<UserDTO> result = userService.getUsers("new User()");
        Assert.assertEquals(List.of(new UserDTO(null, "username", "password", "email", "name")), result);
    }

    /**
     * Test list all.
     *
     * @throws Exception the exception
     */
    @Test
    public void testListAll() throws Exception {
        when(userFactory.toUserDTO(any())).thenReturn(new UserDTO(null, "username", "password", "email", "name"));

        List<UserDTO> result = userService.getAll();
        Assert.assertEquals(List.of(new UserDTO(null, "username", "password", "email", "name")), result);
    }
}