package com.film_radar.film_radar_backend.controllers;

import com.film_radar.film_radar_backend.models.Movie;
import com.film_radar.film_radar_backend.services.MovieService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
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
}
