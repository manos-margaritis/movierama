package com.abandonedlabs.movierama.repository;

import com.abandonedlabs.movierama.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * The interface Movie repository.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

	/**
	 * Find by title movie.
	 *
	 * @param title the title
	 * @return the movie
	 */
	Movie findByTitle(String title);

	/**
	 * Find by external id movie.
	 *
	 * @param externalId the external id
	 * @return the movie
	 */
	Movie findByExternalId(UUID externalId);

	/**
	 * Find by publisher external id list.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<Movie> findByPublisher_ExternalId(UUID userId);
}