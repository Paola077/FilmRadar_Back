package com.film_radar.film_radar_backend.services;

import com.film_radar.film_radar_backend.models.Movie;
import com.film_radar.film_radar_backend.repositories.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final ApiService apiService;
    private final MovieRepository movieRepository;

    public MovieService(ApiService apiService, MovieRepository movieRepository){
        this.apiService = apiService;
        this.movieRepository = movieRepository;
    }

    public List<Movie> fetchAndSaveMovies(int pageNumber) {
        List<Movie> moviesFromApi = apiService.getAllMovies(pageNumber);
        List<Movie> savedMovies = new ArrayList<>();

        for (Movie movie : moviesFromApi) {
            if (!movieRepository.existsByTmdbId(movie.getTmdbId())) {
                Movie savedMovie = movieRepository.save(movie);
                savedMovies.add(savedMovie);
            }
        }
        return savedMovies;
    }

    public List<Movie> getAllMoviesFromDb(){
        return movieRepository.findAll();
    }

}
