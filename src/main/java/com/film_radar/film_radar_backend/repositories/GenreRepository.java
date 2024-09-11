package com.film_radar.film_radar_backend.repositories;

import com.film_radar.film_radar_backend.models.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
