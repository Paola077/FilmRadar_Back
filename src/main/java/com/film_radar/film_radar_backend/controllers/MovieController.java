package com.film_radar.film_radar_backend.controllers;

import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.models.DTO.MovieDTO;
import com.film_radar.film_radar_backend.models.DTO.ReviewDTO;
import com.film_radar.film_radar_backend.services.MovieService;
import com.film_radar.film_radar_backend.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;
    ReviewService reviewService;

    public MovieController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @PostMapping("/movie/fetch")
    public ResponseEntity<List<Movie>> fetchAndSaveMovies(@RequestParam int pagesMovies) {
        List<Movie> savedMovies = movieService.fetchAndSaveMovies(pagesMovies);
        return ResponseEntity.ok(savedMovies);
    }

    @GetMapping("/discover/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMoviesFromDb();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/auth/all/movies")
    public ResponseEntity<List<MovieDTO>> getAllMoviesPublic() {
        List<MovieDTO> movies = movieService.getAllMoviesPublic();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/auth/search/title")
    public ResponseEntity<List<MovieDTO>> searchByTitle(@RequestParam String title) {
        List<MovieDTO> movies = movieService.findByTitle(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/auth/search/genre")
    public ResponseEntity<List<MovieDTO>> searchByGenre(@RequestParam int genreId) {
        List<MovieDTO> movies = movieService.findByGenre(genreId);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/auth/search/year")
    public ResponseEntity<List<MovieDTO>> searchByYear(@RequestParam String year) {
        List<MovieDTO> movies = movieService.findByYear(year);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/api/{movieId}/rate")
    public ResponseEntity<MovieDTO> rateMovie(@PathVariable int movieId, @RequestParam int popularity) {
        MovieDTO ratedMovie = movieService.rateMovie(movieId, popularity);
        return ResponseEntity.ok(ratedMovie);
    }

    @GetMapping("/auth/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByMovie(@PathVariable int movieId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByMovie(movieId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/api/{movieId}/addReview")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable int movieId,
                                               @RequestParam int userId,
                                               @RequestParam String reviewText,
                                               @RequestParam int popularity) {
        ReviewDTO review = movieService.addReviewToMovie(movieId, reviewText, userId, popularity);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/api/{movieId}/favorites")
    public ResponseEntity<Void> addToFavorites(@PathVariable int movieId, @RequestParam int userId) {
        movieService.addToFavorites(movieId, userId);
        return ResponseEntity.ok().build();
    }
}
