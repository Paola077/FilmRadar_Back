package com.film_radar.film_radar_backend.services;

import com.film_radar.film_radar_backend.models.Review;
import com.film_radar.film_radar_backend.repositories.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public ResponseEntity<Object> addNewReview(Review review){
        Optional<Review> existReview = reviewRepository.findById(review.getUser().getId());
        if(existReview.isPresent()){
            return new ResponseEntity<>("El usuario ya ha calificado esta película", HttpStatus.CONFLICT);
        } else {
            review.setCreatedAt(new Date().getTime());
            reviewRepository.save(review);
            return new ResponseEntity<>("Review añadido con éxito!", HttpStatus.CREATED);

        }
    }
}
