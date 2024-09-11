package com.film_radar.film_radar_backend.repositories;

import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.models.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findById(int review);
    List<Review> findByMovie(Movie movie);
}
