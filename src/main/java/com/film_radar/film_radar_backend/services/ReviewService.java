package com.film_radar.film_radar_backend.services;

import com.film_radar.film_radar_backend.mapper.MovieMapper;
import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.models.Entity.Review;
import com.film_radar.film_radar_backend.models.DTO.ReviewDTO;
import com.film_radar.film_radar_backend.repositories.MovieRepository;
import com.film_radar.film_radar_backend.repositories.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, MovieMapper movieMapper){

        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public ResponseEntity<Object> addNewReview(Review review) {
        Optional<Review> existReview = reviewRepository.findById(review.getUser().getId());
        if (existReview.isPresent()) {
            return new ResponseEntity<>("El usuario ya ha calificado esta película", HttpStatus.CONFLICT);
        } else {
            review.setCreateAd(new Date().getTime());
            reviewRepository.save(review);
            return new ResponseEntity<>("Review añadido con éxito!", HttpStatus.CREATED);
        }
    }

    public List<ReviewDTO> getReviewsByMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + movieId));

        List<Review> reviews = reviewRepository.findByMovie(movie);
        return reviews.stream()
                .map(movieMapper::convertReviewToDTO)
                .collect(Collectors.toList());
    }
}
