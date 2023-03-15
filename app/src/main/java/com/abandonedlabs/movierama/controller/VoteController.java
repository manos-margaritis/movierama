package com.abandonedlabs.movierama.controller;

import com.abandonedlabs.movierama.dto.VoteDTO;
import com.abandonedlabs.movierama.service.VoteService;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The type Vote controller.
 */
@RestController
@Api(value = "Vote API", tags = "Votes")
@RequestMapping(value = "/api/v1/votes")
public class VoteController {

    private final static Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService service;

    /**
     * Add or update vote response entity.
     *
     * @param userId  the user id
     * @param voteDto the vote dto
     * @return the response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.PUT)
    @ApiOperation(value = "Adds or updates a vote to the movie", response = String.class)
    @ApiImplicitParam(
            name = "Authorization",
            value = "Bearer <The user's access token obtained upon registration or authentication>",
            example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoibG9sb3MiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjc4NTUyMzA3LCJleHAiOjE2Nzg1NTI5MDd9.im-0ZONNxD_5ajcRrGUaeiNVL9lK5WaSqF77gONhJZH6QcFw9ZOfCIRQzfRwJ5IoJQ_2ZC50H1mc3byFlS9FRw",
            required = true,
            dataType = "string",
            paramType = "header"
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The vote is accepted!", response = String.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "server error")})
    public ResponseEntity<String> addOrUpdateVote(@RequestParam UUID userId, @Valid @RequestBody VoteDTO voteDto) throws Exception {
        logger.debug("addOrUpdateVote({}, {})", userId, voteDto);
        ResponseEntity<String> response;
        String result = service.addOrUpdateVote(userId, voteDto);
        response = ResponseEntity.ok().body(result);

        return response;
    }


    /**
     * Remove vote response entity.
     *
     * @param userId the user id
     * @param movie  the movie
     * @return the response entity
     * @throws Exception the exception
     */
    @ApiOperation(value = "Removes a vote from a movie", response = String.class)
    @RequestMapping(value = "", produces = {"application/json"},	method = RequestMethod.DELETE)
    @ApiImplicitParam(
            name = "Authorization",
            value = "Bearer <The user's access token obtained upon registration or authentication>",
            example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoibG9sb3MiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjc4NTUyMzA3LCJleHAiOjE2Nzg1NTI5MDd9.im-0ZONNxD_5ajcRrGUaeiNVL9lK5WaSqF77gONhJZH6QcFw9ZOfCIRQzfRwJ5IoJQ_2ZC50H1mc3byFlS9FRw",
            required = true,
            dataType = "string",
            paramType = "header"
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeVote(@RequestParam UUID userId, @RequestParam UUID movie) throws Exception {
        logger.debug("removeVote({}, {})", userId, movie);
        ResponseEntity<String> response;
        String result = service.delete(userId, movie);
        response = ResponseEntity.status(HttpStatus.OK).body(result);

        return response;
    }

    /**
     * Gets vote.
     *
     * @param userId the user id
     * @param movie  the movie
     * @return the vote
     * @throws Exception the exception
     */
    @ApiOperation(value = "Lists a vote on the movie", response = VoteDTO.class)
    @RequestMapping(value = "", produces = {"application/json"},	method = RequestMethod.GET)
    @ApiImplicitParam(
            name = "Authorization",
            value = "Bearer <The user's access token obtained upon registration or authentication>",
            example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoibG9sb3MiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjc4NTUyMzA3LCJleHAiOjE2Nzg1NTI5MDd9.im-0ZONNxD_5ajcRrGUaeiNVL9lK5WaSqF77gONhJZH6QcFw9ZOfCIRQzfRwJ5IoJQ_2ZC50H1mc3byFlS9FRw",
            required = true,
            dataType = "string",
            paramType = "header"
    )
    public ResponseEntity<VoteDTO> getVote(@RequestParam UUID userId, @RequestParam UUID movie) throws Exception {
        logger.debug("getVote({}, {})", userId, movie);
        VoteDTO vote = service.list(userId, movie);
        ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(vote);

        return response;
    }
}
