package com.film_radar.film_radar_backend.mapper;

import com.film_radar.film_radar_backend.models.Genre;
import com.film_radar.film_radar_backend.models.Movie;
import com.film_radar.film_radar_backend.models.MovieDTO;
import com.film_radar.film_radar_backend.repositories.GenreRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MovieMapper {

    private GenreRepository genreRepository;

    public Movie mapDtoToEntity(MovieDTO movieDTO, Set<Genre> genres) {
        Movie movie = new Movie();

        movie.setTitle(movieDTO.getTitle());
        movie.setOverview(movieDTO.getOverview());
        movie.setBackdrop_path(movieDTO.getBackdropPath());
        movie.setTmdbId(movieDTO.getTmdb_Id());
        movie.setRelease_date(movieDTO.getReleaseDate());
        movie.setPopularity(movieDTO.getPopularity());
        movie.setGenres(genres);

        return movie;
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
}
