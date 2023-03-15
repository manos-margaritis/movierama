package com.abandonedlabs.movierama.factory;

import com.abandonedlabs.movierama.dto.MovieDTO;
import com.abandonedlabs.movierama.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * The type Movie factory.
 */
@Component
public class MovieFactory {

    @Autowired
    private UserFactory userFactory;

    /**
     * To movie dto movie dto.
     *
     * @param movie the movie
     * @return the movie dto
     */
    public MovieDTO toMovieDTO(Movie movie) {
        return MovieDTO.builder()
                .withId(movie.getExternalId())
                .withTitle(movie.getTitle())
                .withDescription(movie.getDescription())
                .withPublicationDate(movie.getPublicationDate())
                .withPublisher(userFactory.toUserDTO(movie.getPublisher()))
                .withLikes(Long.valueOf(movie.getFans().size()))
                .withHates(Long.valueOf(movie.getHaters().size()))
                .build();
    }

    /**
     * From movie dto movie.
     *
     * @param movieDTO the movie dto
     * @return the movie
     */
    public Movie fromMovieDTO(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setPublicationDate(Objects.nonNull(movieDTO.getPublicationDate()) ? movieDTO.getPublicationDate() : ZonedDateTime.now());
        return movie;
    }

}
