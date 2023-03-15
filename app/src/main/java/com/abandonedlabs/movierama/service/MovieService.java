package com.abandonedlabs.movierama.service;

import com.abandonedlabs.movierama.dto.MovieDTO;
import com.abandonedlabs.movierama.factory.MovieFactory;
import com.abandonedlabs.movierama.model.Movie;
import com.abandonedlabs.movierama.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The type Movie service.
 */
@Service
public class MovieService {

    private final static Logger logger = LoggerFactory.getLogger(MovieService.class);

    /**
     * The Movie repository.
     */
    @Autowired
    MovieRepository movieRepository;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    @Autowired
    private MovieFactory movieFactory;

    /**
     * Create movie movie dto.
     *
     * @param userExternalId the user external id
     * @param movieDTO       the movie dto
     * @return the movie dto
     */
    @Transactional
    public MovieDTO createMovie(UUID userExternalId, MovieDTO movieDTO) {
        logger.debug("create({}, {})", userExternalId, movieDTO);
        Movie movie = movieRepository.findByTitle(movieDTO.getTitle());

        if (movie != null) {
            throw new IllegalArgumentException("Movie with tile: '" + movie.getTitle() + "' already exists!");
        } else {
            // Add new Movie in DB
            movie = movieFactory.fromMovieDTO(movieDTO);
            movie.setPublisher(userService.find(userExternalId));
            movieRepository.save(movie);

            logger.info("New Movie added: " + movie);
        }

        return movieFactory.toMovieDTO(movie);
    }

    /**
     * List movies list.
     *
     * @param userId the user id
     * @return the list
     */
    @Transactional
    public List<MovieDTO> listMovies(UUID userId) {
        logger.debug("list({})", userId);
        List<MovieDTO> movieDtos;

        if (userId != null) {
            movieDtos = movieRepository.findByPublisher_ExternalId(userId)
                    .stream()
                    .map(movie -> movieFactory.toMovieDTO(movie))
                    .collect(Collectors.toList());

        } else {
            // Fetch all movies
            movieDtos =  movieRepository.findAll()
                    .stream()
                    .map(movie -> movieFactory.toMovieDTO(movie))
                    .collect(Collectors.toList());
        }

        return movieDtos;
    }

    /**
     * List all by likes list.
     *
     * @param userId the user id
     * @return the list
     */
    @Transactional
    public List<MovieDTO> listAllByLikes(UUID userId) {
        logger.debug("listAllByLikes({})", userId);
        return movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getFans().stream().anyMatch(f -> f.getExternalId().equals(userId)))
                .map(movie -> movieFactory.toMovieDTO(movie))
                .collect(Collectors.toList());
    }

    /**
     * List all by hates list.
     *
     * @param userId the user id
     * @return the list
     */
    @Transactional
    public List<MovieDTO> listAllByHates(UUID userId) {
        logger.debug("listAllByHates({})", userId);
        return movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getHaters().stream().anyMatch(f -> f.getExternalId().equals(userId)))
                .map(movie -> movieFactory.toMovieDTO(movie))
                .collect(Collectors.toList());
    }
}