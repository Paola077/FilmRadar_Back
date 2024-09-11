package com.film_radar.film_radar_backend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_radar.film_radar_backend.mapper.MovieMapper;
import com.film_radar.film_radar_backend.models.Entity.Genre;
import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.models.DTO.MovieDTO;
import com.film_radar.film_radar_backend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApiService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;


    private final RestTemplate restTemplate;
    private final MovieMapper movieMapper;
    private final GenreService genreService;
    private final GenreRepository genreRepository;

    public ApiService(RestTemplate restTemplate, MovieMapper movieMapper, GenreService genreService, GenreRepository genreRepository) {
        this.restTemplate = restTemplate;
        this.movieMapper = movieMapper;
        this.genreService = genreService;
        this.genreRepository = genreRepository;
    }


    public List<Movie> getAllMovies(int pageNumber) {
        genreService.fetchAndSaveGenres();
        String url = buildUrl("/discover/movie", "", pageNumber);
        String jsonResponse = restTemplate.getForObject(url, String.class);

        List<MovieDTO> movieDTOs = parseMoviesFromJson(jsonResponse);

        Set<Integer> genreIds = movieDTOs.stream()
                .flatMap(movieDTO -> movieDTO.getGenreIds().stream())
                .collect(Collectors.toSet());

        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(genreIds));

        return movieDTOs.stream()
                .map(movieDTO -> movieMapper.mapDtoToEntity(movieDTO, genres))
                .collect(Collectors.toList());
    }

    private String buildUrl(String endpoint, String queryParams, int page) {
        return UriComponentsBuilder.fromHttpUrl(apiUrl + endpoint)
                .queryParam("api_key", apiKey)
                .queryParam("language", "es-ES")
                .queryParam("page", page)
                .queryParam(queryParams)
                .toUriString();
    }

    private List<MovieDTO> parseMoviesFromJson(String jsonResponse) {
        List<MovieDTO> movieDTOs = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");
            movieDTOs = objectMapper.readValue(resultsNode.toString(), new TypeReference<List<MovieDTO>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieDTOs;
    }
}