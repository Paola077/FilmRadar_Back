package com.film_radar.film_radar_backend.controllers;

import com.film_radar.film_radar_backend.models.Review;
import com.film_radar.film_radar_backend.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReviewController {

    ReviewService reviewService;

    @PostMapping("/api/review")
    public ResponseEntity<Object> createReview(@RequestBody
    Review review){
        review.setCreatedAt(new Date().getTime());
        return reviewService.addNewReview(review);
    }
}
