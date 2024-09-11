package com.film_radar.film_radar_backend.repositories;

import com.film_radar.film_radar_backend.models.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findById(int rating);
}
