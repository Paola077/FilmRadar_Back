package com.film_radar.film_radar_backend.controllers;

import com.film_radar.film_radar_backend.models.Rating;
import com.film_radar.film_radar_backend.services.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RatingController {

    RatingService ratingService;

    @PostMapping("/api/rating")
    public ResponseEntity<Object> createRating(@RequestBody Rating rating){
        rating.setCreatedAt(new Date().getTime());
        return ratingService.addNewRating(rating);
    }
}
