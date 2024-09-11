package com.film_radar.film_radar_backend.models.Entity;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.Set;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "release_date", nullable = false)
    private String releaseDate;

    @Column(name = "popularity", nullable = false)
    private float popularity;

    @Column(name = "overview", length = 2000, nullable = false)
    private String overview;

    @Column(name = "backdrop_path", length = 500, nullable = false )
    private String backdrop_path;

    @Column(name = "tmdb_id", unique = true, nullable = false)
    private int tmdbId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id" ),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )

    private Set<Genre> genres = Collections.emptySet();

   @ManyToMany(mappedBy = "favoriteMovies")
    private Set<User> usersFavorited = Collections.emptySet();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<User> getUsersFavorited() {
        return usersFavorited;
    }

    public void setUsersFavorited(Set<User> usersFavorited) {
        this.usersFavorited = usersFavorited;
    }
}
