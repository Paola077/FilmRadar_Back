package com.film_radar.film_radar_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

    private String title;
    private String overview;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("id")
    private int tmdb_Id;
    @JsonProperty("release_date")
    private String releaseDate;
    private float popularity;
    @JsonProperty("genre_ids")
    private Set<Integer> genreIds = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getTmdb_Id() {
        return tmdb_Id;
    }

    public void setTmdb_Id(int tmdb_Id) {
        this.tmdb_Id = tmdb_Id;
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

    public Set<Integer> getGenreIds() {
        return genreIds;
    }

    @JsonSetter("genre_ids")
    public void setGenreIds(Set<Integer> genreIds) {
        if (genreIds == null) {
            this.genreIds = new HashSet<>();
        } else {
            this.genreIds = genreIds;
        }
    }
}
