package com.film_radar.film_radar_backend.models.Entity;

import jakarta.persistence.*;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "ratings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "movie_id"})
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "popularity", nullable = false)
    @Min(1)
    @Max(10)
    private int popularity;

    @Column(name = "create_at", nullable = false)
    private Long CreatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Min(1)
    @Max(10)
    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(@Min(1) @Max(10) int popularity) {
        this.popularity = popularity;
    }

    public Long getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Long createdAt) {
        CreatedAt = createdAt;
    }
}
