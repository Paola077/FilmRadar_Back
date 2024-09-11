package com.film_radar.film_radar_backend.services;

import com.film_radar.film_radar_backend.mapper.MovieMapper;
import com.film_radar.film_radar_backend.models.DTO.MovieDTO;
import com.film_radar.film_radar_backend.models.DTO.ReviewDTO;
import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.models.Entity.Review;
import com.film_radar.film_radar_backend.models.Entity.User;
import com.film_radar.film_radar_backend.repositories.MovieRepository;
import com.film_radar.film_radar_backend.repositories.ReviewRepository;
import com.film_radar.film_radar_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final ApiService apiService;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public MovieService(ApiService apiService, MovieRepository movieRepository, MovieMapper movieMapper, ReviewRepository reviewRepository, UserRepository userRepository){
        this.apiService = apiService;
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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

    public List<MovieDTO> getAllMoviesPublic() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movieMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        return movies.stream()
                .map(movieMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findByGenre(int genreId) {
        List<Movie> movies = movieRepository.findByGenresId(genreId);
        return movies.stream()
                .map(movieMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findByYear(String year) {
        List<Movie> movies = movieRepository.findByReleaseDateContaining(year);
        return movies.stream()
                .map(movieMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO rateMovie(int movieId, int popularity) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        if (popularity < 1 || popularity > 10) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 10");
        }

        movie.setPopularity(popularity);
        Movie updatedMovie = movieRepository.save(movie);

        return movieMapper.convertToDTO(updatedMovie);
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

    public void addToFavorites(int movieId, int userId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + movieId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        user.getFavoriteMovies().add(movie);
        userRepository.save(user);
    }
}
