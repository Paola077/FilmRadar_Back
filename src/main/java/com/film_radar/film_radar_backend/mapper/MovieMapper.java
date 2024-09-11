package com.film_radar.film_radar_backend.mapper;

import com.film_radar.film_radar_backend.models.DTO.MovieDTO;
import com.film_radar.film_radar_backend.models.DTO.ReviewDTO;
import com.film_radar.film_radar_backend.models.Entity.Genre;
import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.models.Entity.Review;
import com.film_radar.film_radar_backend.repositories.GenreRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    private GenreRepository genreRepository;

    public Movie mapDtoToEntity(MovieDTO movieDTO, Set<Genre> genres) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setBackdrop_path(movieDTO.getBackdropPath());
        movie.setTmdbId(movieDTO.getTmdb_Id());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setPopularity(movieDTO.getPopularity());
        movie.setGenres(genres);
        return movie;
    }

    public MovieDTO convertToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setOverview(movie.getOverview());
        movieDTO.setBackdropPath(movie.getBackdrop_path());
        movieDTO.setTmdb_Id(movie.getTmdbId());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setPopularity(movie.getPopularity());
        movieDTO.setGenreIds(movie.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        return movieDTO;
    }

    public Set<Genre> mapGenreIdsToEntities(Set<Integer> genreIds) {
        Set<Genre> genres = new HashSet<>();
        for (Integer genreId : genreIds) {
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new RuntimeException("Género no encontrado: " + genreId));
            genres.add(genre);
        }
        return genres;
    }

    public ReviewDTO convertReviewToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewText(review.getReviewText());
        reviewDTO.setPopularity(review.getPopularity());
        reviewDTO.setMovieId(review.getMovie().getId());
        reviewDTO.setUserId(review.getUser().getId());
        reviewDTO.setUserName(review.getUser().getName());
        return reviewDTO;
    }


}
