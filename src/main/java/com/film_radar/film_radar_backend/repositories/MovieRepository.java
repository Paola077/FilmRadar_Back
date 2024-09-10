package com.film_radar.film_radar_backend.repositories;

import com.film_radar.film_radar_backend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findById(int movieId);
    boolean existsByTmdbId(int tmdbId);
}
