package com.abandonedlabs.movierama.service;

import com.abandonedlabs.movierama.dto.VoteDTO;
import com.abandonedlabs.movierama.model.Movie;
import com.abandonedlabs.movierama.model.User;
import com.abandonedlabs.movierama.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

/**
 * The type Vote service test.
 */
public class VoteServiceTest {
    /**
     * The Logger.
     */
    @Mock
    Logger logger;
    /**
     * The Movie repository.
     */
    @Mock
    MovieRepository movieRepository;
    /**
     * The User service.
     */
    @Mock
    UserService userService;
    /**
     * The Vote service.
     */
    @InjectMocks
    VoteService voteService;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test put.
     *
     * @throws Exception the exception
     */
    @Test
    public void testPut() throws Exception {
        when(movieRepository.findByExternalId(any())).thenReturn(new Movie());
        when(userService.find(any())).thenReturn(new User());

        String result = voteService.addOrUpdateVote(null, new VoteDTO(null));
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    /**
     * Test delete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDelete() throws Exception {
        when(movieRepository.findByExternalId(any())).thenReturn(new Movie());
        when(userService.find(any())).thenReturn(new User());

        String result = voteService.delete(null, null);
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    /**
     * Test list.
     *
     * @throws Exception the exception
     */
    @Test
    public void testList() throws Exception {
        when(movieRepository.findByExternalId(any())).thenReturn(new Movie());
        when(userService.find(any())).thenReturn(new User());

        VoteDTO result = voteService.list(null, null);
        Assert.assertEquals(new VoteDTO(null), result);
    }
}