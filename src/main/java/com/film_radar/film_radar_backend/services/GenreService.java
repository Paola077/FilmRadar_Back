package com.film_radar.film_radar_backend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_radar.film_radar_backend.models.Genre;
import com.film_radar.film_radar_backend.models.GenreDTO;
import com.film_radar.film_radar_backend.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private GenreRepository genreRepository;
    private final RestTemplate restTemplate;

    public GenreService(GenreRepository genreRepository, RestTemplate restTemplate) {
        this.genreRepository = genreRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    public void fetchAndSaveGenres() {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl + "/genre/movie/list")
                .queryParam("api_key", apiKey)
                .queryParam("language", "es-ES")
                .toUriString();
        String jsonResponse = restTemplate.getForObject(url, String.class);
        List<GenreDTO> genreDTOs = parseGenresFromJson(jsonResponse);

        for (GenreDTO genreDTO : genreDTOs) {
            if (!genreRepository.existsById(genreDTO.getId())) {
                Genre genre = new Genre();
                genre.setId(genreDTO.getId());
                genre.setGenre(genreDTO.getName());
                genreRepository.save(genre);
            }
        }

}

    private List<GenreDTO> parseGenresFromJson(String jsonResponse) {
        List<GenreDTO> genreDTOs = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode genresNode = rootNode.path("genres");
            genreDTOs = objectMapper.readValue(genresNode.toString(), new TypeReference<List<GenreDTO>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genreDTOs;
    }
}


