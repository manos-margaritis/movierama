package com.abandonedlabs.movierama.service;

import com.abandonedlabs.movierama.dto.MovieDTO;
import com.abandonedlabs.movierama.dto.UserDTO;
import com.abandonedlabs.movierama.factory.MovieFactory;
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

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * The type Movie service test.
 */
public class MovieServiceTest {
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
     * The Movie factory.
     */
    @Mock
    MovieFactory movieFactory;
    /**
     * The Movie service.
     */
    @InjectMocks
    MovieService movieService;

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
        when(movieRepository.findByTitle(anyString())).thenReturn(new Movie());
        when(userService.find(any())).thenReturn(new User());
        when(movieFactory.toMovieDTO(any())).thenReturn(new MovieDTO("title", null, null, null, null, null, null));
        when(movieFactory.fromMovieDTO(any())).thenReturn(new Movie());

        MovieDTO result = movieService.createMovie(null, new MovieDTO("title", null, null, null, null, null, null));
        Assert.assertEquals(new MovieDTO("title", null, null, null, null, null, null), result);
    }

    /**
     * Test list.
     *
     * @throws Exception the exception
     */
    @Test
    public void testList() throws Exception {
        when(movieRepository.findByPublisher_ExternalId(any())).thenReturn(List.of(new Movie()));
        when(movieFactory.toMovieDTO(any())).thenReturn(new MovieDTO("title", "description", null, new UserDTO(null, "username", "password", "email", "name"), null, Long.valueOf(1), Long.valueOf(1)));

        List<MovieDTO> result = movieService.listMovies(null);
        Assert.assertEquals(List.of(new MovieDTO("title", "description", null, new UserDTO(null, "username", "password", "email", "name"), null, Long.valueOf(1), Long.valueOf(1))), result);
    }

    /**
     * Test list all by likes.
     *
     * @throws Exception the exception
     */
    @Test
    public void testListAllByLikes() throws Exception {
        when(movieFactory.toMovieDTO(any())).thenReturn(new MovieDTO("title", "description", null, new UserDTO(null, "username", "password", "email", "name"), null, Long.valueOf(1), Long.valueOf(1)));

        List<MovieDTO> result = movieService.listAllByLikes(null);
        Assert.assertEquals(List.of(new MovieDTO("title", "description", null, new UserDTO(null, "username", "password", "email", "name"), null, Long.valueOf(1), Long.valueOf(1))), result);
    }

    /**
     * Test list all by hates.
     *
     * @throws Exception the exception
     */
    @Test
    public void testListAllByHates() throws Exception {
        when(movieFactory.toMovieDTO(any())).thenReturn(new MovieDTO("title", "description", null, new UserDTO(null, "username", "password", "email", "name"), null, Long.valueOf(1), Long.valueOf(1)));

        List<MovieDTO> result = movieService.listAllByHates(null);
        Assert.assertEquals(List.of(new MovieDTO("title", "description", null, new UserDTO(null, "username", "password", "email", "name"), null, Long.valueOf(1), Long.valueOf(1))), result);
    }
}