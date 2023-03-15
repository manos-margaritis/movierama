package com.abandonedlabs.movierama.controller;

import com.abandonedlabs.movierama.dto.MovieDTO;
import com.abandonedlabs.movierama.service.MovieService;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The type Movie controller.
 */
@RestController
@Api(value = "Movies API", tags = "Movies")
@RequestMapping(value = "/api/v1/movies")
public class MovieController {

    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService service;

    /**
     * Create movie response entity.
     *
     * @param userId   the user id
     * @param movieDTO the movie dto
     * @return the response entity
     */
    @RequestMapping(value = "", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "Creates a new movie", response = MovieDTO.class)
    @ApiImplicitParam(
            name = "Authorization",
            value = "Bearer <The user's access token obtained upon registration or authentication>",
            example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoibG9sb3MiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjc4NTUyMzA3LCJleHAiOjE2Nzg1NTI5MDd9.im-0ZONNxD_5ajcRrGUaeiNVL9lK5WaSqF77gONhJZH6QcFw9ZOfCIRQzfRwJ5IoJQ_2ZC50H1mc3byFlS9FRw",
            required = true,
            dataType = "string",
            paramType = "header"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The movie is created!", response = MovieDTO.class),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 500, message = "Internal Server error")})
    public ResponseEntity<MovieDTO> createMovie(@RequestParam UUID userId, @Valid @RequestBody MovieDTO movieDTO) {
        logger.debug("createMovie({}, {})", userId, movieDTO);
        ResponseEntity<MovieDTO> response;
        MovieDTO data = service.createMovie(userId, movieDTO);
        response = ResponseEntity.status(HttpStatus.CREATED).body(data);

        return response;
    }

    /**
     * List movies response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @ApiOperation(value = "Lists all the movies", response = MovieDTO.class, responseContainer = "List")
    @RequestMapping(value = "", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<Iterable> listMovies(@RequestParam (required = false) UUID userId) {
        logger.debug("listMovies({})", userId);
        List<MovieDTO> movies = service.listMovies(userId);
        ResponseEntity<Iterable> response;
        response = ResponseEntity.status(HttpStatus.OK).body(movies);

        return response;
    }

    /**
     * List liked movies response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @ApiOperation(value = "Lists all liked movies", response = MovieDTO.class, responseContainer = "List")
    @RequestMapping(value = "/likes", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<Iterable> listLikedMovies(@RequestParam (required = false) UUID userId) {
        logger.debug("listLikedMovies({})", userId);
        List<MovieDTO> movies = service.listAllByLikes(userId);
        ResponseEntity<Iterable> response;
        response = ResponseEntity.status(HttpStatus.OK).body(movies);

        return response;
    }

    /**
     * List hated movies response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @ApiOperation(value = "Lists all hated movies", response = MovieDTO.class, responseContainer = "List")
    @RequestMapping(value = "/hates", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<Iterable> listHatedMovies(@RequestParam (required = false) UUID userId) {
        logger.debug("listHatedMovies({})", userId);
        List<MovieDTO> movies = service.listAllByHates(userId);
        ResponseEntity<Iterable> response;
        response = ResponseEntity.status(HttpStatus.OK).body(movies);

        return response;
    }
}
