package com.film_radar.film_radar_backend.repositories;

import com.film_radar.film_radar_backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    Optional<Review> findById(int review);
}
