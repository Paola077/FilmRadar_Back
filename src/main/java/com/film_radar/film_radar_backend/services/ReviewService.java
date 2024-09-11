package com.film_radar.film_radar_backend.services;

import com.film_radar.film_radar_backend.mapper.MovieMapper;
import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.models.Entity.Review;
import com.film_radar.film_radar_backend.models.DTO.ReviewDTO;
import com.film_radar.film_radar_backend.models.Entity.User;
import com.film_radar.film_radar_backend.repositories.MovieRepository;
import com.film_radar.film_radar_backend.repositories.ReviewRepository;
import com.film_radar.film_radar_backend.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            MovieRepository movieRepository,
            MovieMapper movieMapper,
            UserRepository userRepository
    ){
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.userRepository = userRepository;
    }

    public ReviewDTO addReviewToMovie(int movieId, String reviewText, int userId, int popularity) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + movieId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        Review review = new Review();
        review.setMovie(movie);
        review.setUser(user);
        review.setReviewText(reviewText);
        review.setPopularity(popularity);
        review.setCreateAd(new Date().getTime());

        Review savedReview = reviewRepository.save(review);

        return movieMapper.convertReviewToDTO(savedReview);
    }

    public List<ReviewDTO> getReviewsByMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + movieId));

        List<Review> reviews = reviewRepository.findByMovie(movie);
        return reviews.stream()
                .map(movieMapper::convertReviewToDTO)
                .collect(Collectors.toList());
    }

    public Boolean updateReviewById(int reviewId, String newReview) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent() && !newReview.isEmpty()) {
            Review reviewToUpdate = review.get();
            reviewToUpdate.setReviewText(newReview);
            reviewRepository.save(reviewToUpdate);
            return true;
        }
        return false;
    }

    public Boolean deleteReviewById(int reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
