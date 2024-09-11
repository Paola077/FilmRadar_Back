package com.film_radar.film_radar_backend;

import com.film_radar.film_radar_backend.controllers.MovieController;
import com.film_radar.film_radar_backend.models.DTO.MovieDTO;
import com.film_radar.film_radar_backend.models.DTO.ReviewDTO;
import com.film_radar.film_radar_backend.models.Entity.Movie;
import com.film_radar.film_radar_backend.services.MovieService;
import com.film_radar.film_radar_backend.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Mock
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = Collections.singletonList(new Movie());
        when(movieService.getAllMoviesFromDb()).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.getAllMovies();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(movies, response.getBody());
    }

    @Test
    public void testGetAllMoviesPublic() {
        List<MovieDTO> movieDTOs = Collections.singletonList(new MovieDTO());
        when(movieService.getAllMoviesPublic()).thenReturn(movieDTOs);

        ResponseEntity<List<MovieDTO>> response = movieController.getAllMoviesPublic();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(movieDTOs, response.getBody());
    }

    @Test
    public void testSearchByTitle() {
        List<MovieDTO> movieDTOs = Collections.singletonList(new MovieDTO());
        when(movieService.findByTitle("Cerrar el círculo")).thenReturn(movieDTOs);

        ResponseEntity<List<MovieDTO>> response = movieController.searchByTitle("Cerrar el círculo");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(movieDTOs, response.getBody());
    }

    @Test
    public void testAddReview() {
        ReviewDTO reviewDTO = new ReviewDTO();
        when(reviewService.addReviewToMovie(1, "película recomendada", 1, 5)).thenReturn(reviewDTO);

        ResponseEntity<ReviewDTO> response = movieController.addReview(1, 1, "película recomendada", 5);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reviewDTO, response.getBody());
    }
}
