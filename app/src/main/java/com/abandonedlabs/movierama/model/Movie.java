package com.abandonedlabs.movierama.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * The type Movie.
 */
@Entity
@Cacheable
@DynamicUpdate
@DynamicInsert
@Table(name = "MOVIE", schema = "public")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID externalId;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User publisher;

    private ZonedDateTime publicationDate;

    /**
     * The Fans.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MOVIE_LIKES",
            joinColumns = {@JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") },
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }
    )
    public Set<User> fans = new HashSet<>();

    /**
     * The Haters.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MOVIE_HATES",
            joinColumns = {@JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") },
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }
    )
    public Set<User> haters = new HashSet<>();

    /**
     * Instantiates a new Movie.
     */
    public Movie() {
        this.externalId = UUID.randomUUID();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets external id.
     *
     * @return the external id
     */
    public UUID getExternalId() {
        return externalId;
    }

    /**
     * Sets external id.
     *
     * @param externalId the external id
     */
    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public User getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets publication date.
     *
     * @return the publication date
     */
    public ZonedDateTime getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets publication date.
     *
     * @param publicationDate the publication date
     */
    public void setPublicationDate(ZonedDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Gets fans.
     *
     * @return the fans
     */
    public Set<User> getFans() {
        if (Objects.isNull(fans)) {
            fans = new HashSet<>();
        }
        return fans;
    }

    /**
     * Sets fans.
     *
     * @param fans the fans
     */
    public void setFans(Set<User> fans) {
        this.fans = fans;
    }

    /**
     * Gets haters.
     *
     * @return the haters
     */
    public Set<User> getHaters() {
        if (Objects.isNull(haters)) {
            haters = new HashSet<>();
        }
        return haters;
    }

    /**
     * Sets haters.
     *
     * @param haters the haters
     */
    public void setHaters(Set<User> haters) {
        this.haters = haters;
    }

    /**
     * Add fan.
     *
     * @param user the user
     */
    public void addFan(User user) {
        if (this.fans == null) {
            this.fans = new HashSet<>();
        }

        this.fans.add(user);
    }

    /**
     * Add hater.
     *
     * @param user the user
     */
    public void addHater(User user) {
        if (this.haters == null) {
            this.haters = new HashSet<>();
        }

        this.haters.add(user);
    }

    /**
     * Remove fan.
     *
     * @param user the user
     */
    public void removeFan(User user) {
        if (this.fans == null) {
            this.fans = new HashSet<>();
        }

        this.fans.remove(user);
    }

    /**
     * Remove hater.
     *
     * @param user the user
     */
    public void removeHater(User user) {
        if (this.haters == null) {
            this.haters = new HashSet<>();
        }

        this.haters.remove(user);
    }
}
