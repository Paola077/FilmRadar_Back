package com.film_radar.film_radar_backend.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "genres" )
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "genre", length = 50, nullable = false)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
