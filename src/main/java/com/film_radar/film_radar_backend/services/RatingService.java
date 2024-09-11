package com.film_radar.film_radar_backend.services;

import com.film_radar.film_radar_backend.models.Entity.Rating;
import com.film_radar.film_radar_backend.repositories.RatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public ResponseEntity<Object> addNewRating(Rating rating){
        Optional<Rating> existRating = ratingRepository.findById(rating.getUser().getId());
        if(existRating.isPresent()){
            return new ResponseEntity<>("El usuario ya ha calificado esta película", HttpStatus.CONFLICT);
        } else {
            rating.setCreatedAt(new Date().getTime());
            ratingRepository.save(rating);
            return new ResponseEntity<>("Rating añadido con éxito!", HttpStatus.CREATED);
        }
    }
}
