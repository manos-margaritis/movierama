package com.abandonedlabs.movierama.service;

import com.abandonedlabs.movierama.dto.VoteDTO;
import com.abandonedlabs.movierama.model.Movie;
import com.abandonedlabs.movierama.model.User;
import com.abandonedlabs.movierama.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * The type Vote service.
 */
@Service
public class VoteService {

    private final static Logger logger = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;

    /**
     * Add or update vote string.
     *
     * @param userExternalId the user external id
     * @param voteDTO        the vote dto
     * @return the string
     * @throws Exception the exception
     */
    @Transactional
    public String addOrUpdateVote(UUID userExternalId, VoteDTO voteDTO) throws Exception {
        logger.debug("addOrUpdateVote({}, {})", userExternalId, voteDTO);
        String result;

        Movie movie = movieRepository.findByExternalId(voteDTO.getMovie());
        User user = userService.find(userExternalId);

        if (movie == null) {
            throw new IllegalArgumentException("Movie with ID '" + voteDTO.getMovie() + "' does not exists!");
        } if (user == null) {
            throw new IllegalArgumentException("Access token '" + userExternalId + "' is not valid!");
        } else {
            User publisher = movie.getPublisher();
            Set<User> fans = movie.getFans();
            Set<User> haters = movie.getHaters();

            if (user.equals(publisher)) {
                throw new Exception(publisher.getName() + " is the publisher of '" + movie.getTitle() + "'!");
            } else if (fans.contains(user) && voteDTO.isLike()) {
                throw new Exception(user.getName() + " has already voted '" + movie.getTitle() + "' positively!");
            } else if (haters.contains(user) && !voteDTO.isLike()) {
                throw new Exception(user.getName() + " has already voted '" + movie.getTitle() + "' negatively!");
            } else if (fans.contains(user) && !voteDTO.isLike()) {
                // Change vote to a hate!
                movie.removeFan(user);
                movie.addHater(user);
                movieRepository.save(movie);
                result = user.getName() + " changed the '" + movie.getTitle() + " vote to a hate!";
            } else if (haters.contains(user) && voteDTO.isLike()) {
                // Change vote to a like!
                movie.removeHater(user);
                movie.addFan(user);
                movieRepository.save(movie);
                result = user.getName() + " changed the '" + movie.getTitle() + " vote to a like!";
            } else {
                // New vote!
                String vote;
                if (voteDTO.isLike()) {
                    vote = "positive vote";
                    movie.addFan(user);
                } else {
                    vote = "negative vote";
                    movie.addHater(user);
                }
                movieRepository.save(movie);
                result = "A " +vote+" to '" + movie.getTitle() + "' was added by " + user.getName();
            }

            logger.info(result);

            return result;
        }
    }

    /**
     * Delete string.
     *
     * @param userExternalId  the user external id
     * @param movieExternalId the movie external id
     * @return the string
     * @throws Exception the exception
     */
    @Transactional
    public String delete(UUID userExternalId, UUID movieExternalId) throws Exception {
        logger.debug("delete({}, {})", userExternalId, movieExternalId);
        String result;
        Movie movie = movieRepository.findByExternalId(movieExternalId);
        User user = userService.find(userExternalId);

        if (movie == null) {
            throw new IllegalArgumentException("Movie with ID '" + movieExternalId + "' does not exists!");
        } if (user == null) {
            throw new IllegalArgumentException("Access token '" + userExternalId + "' is not valid!");
        } else {
            Set<User> fans = movie.getFans();
            Set<User> haters = movie.getHaters();

            if (fans.contains(user)) {
                movie.removeFan(user);
                movieRepository.save(movie);
                result = "A positive vote to '" + movie.getTitle() + "' was retracted by " + user.getName();
            } else if (haters.contains(user)) {
                movie.removeHater(user);
                result = "A negative vote to '" + movie.getTitle() + "' was retracted by " + user.getName();
                movieRepository.save(movie);
            } else {
                throw new Exception(user.getName() + " has not voted '" + movie.getTitle() + "' yet!");
            }
        }

        logger.info(result);

        return result;
    }

    /**
     * List vote dto.
     *
     * @param userExternalId  the user external id
     * @param movieExternalId the movie external id
     * @return the vote dto
     * @throws Exception the exception
     */
    @Transactional
    public VoteDTO list(UUID userExternalId, UUID movieExternalId) throws Exception {
        logger.debug("list({}, {})", userExternalId, movieExternalId);
        VoteDTO vote = new VoteDTO(movieExternalId);
        Movie movie = movieRepository.findByExternalId(movieExternalId);
        User user = userService.find(userExternalId);

        if (movie == null) {
            throw new IllegalArgumentException("Movie with ID '" + movieExternalId + "' does not exists!");
        } if (user == null) {
            throw new IllegalArgumentException("Access token '" + userExternalId + "' is not valid!");
        } else {
            Set<User> fans = movie.getFans();
            Set<User> haters = movie.getHaters();

            if (fans.contains(user)) {
                vote.setLike(true);
            } else if (haters.contains(user)) {
                vote.setLike(false);
            } else {
                throw new RuntimeException(user.getName() + " has not voted '" + movie.getTitle() + "' yet!");
            }
        }

        logger.info("User " + user.getName() + " checked vote" + vote);

        return vote;
    }
}