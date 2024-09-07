package com.film_radar.film_radar_backend.models;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "release_year", nullable = false)
    private int release_year;

    @Column(name = "rating", nullable = false)
    private float rating;

    @Column(name = "overview", length = 900, nullable = false)
    private String overview;

    @Column(name = "poster_url", length = 250, nullable = false )
    private String poster_url;

    @Column(name = "tmdb_id", unique = true, nullable = false)
    private int tmdb_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id" ),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )

    private Set<Genre> genres;

   @ManyToMany(mappedBy = "favoriteMovies")

    private Set<User> usersFavorited;

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

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public int getTmdb_id() {
        return tmdb_id;
    }

    public void setTmdb_id(int tmdb_id) {
        this.tmdb_id = tmdb_id;
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
