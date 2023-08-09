package com.movie.api.controller;

import com.movie.api.domain.dto.MovieDTO;
import com.movie.api.domain.mapper.MovieMapper;
import com.movie.api.domain.model.Movie;
import com.movie.api.domain.request.MovieRequest;
import com.movie.api.domain.response.ResponseMessage;
import com.movie.api.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveMovie() {
        MovieRequest request = new MovieRequest();
        // Set up request

        Movie movie = new Movie();
        // Set up movie

        when(movieMapper.mapToEntity(request)).thenReturn(movie);
        when(movieService.saveMovie(movie)).thenReturn(movie);

        MovieDTO movieDTO = new MovieDTO();
        when(movieMapper.mapToDTO(movie)).thenReturn(movieDTO);

        ResponseEntity<MovieDTO> responseEntity = movieController.saveMovie(request);

        // Assert responseEntity, HttpStatus, and interactions with movieService and movieMapper
    }

    // Similar test cases for updateMovie and deleteMovie methods

    @Test
    public void testHandleNotFound() {
        NoSuchElementException exception = new NoSuchElementException("Movie not found!");

        ResponseEntity<ResponseMessage> responseEntity = movieController.handleNotFound(exception);

        // Assert responseEntity, HttpStatus, and response message
    }

    @Test
    public void testHandleBadRequest() {
        ResponseEntity<ResponseMessage> responseEntity = movieController.handleBadRequest();

        // Assert responseEntity, HttpStatus, and response message
    }

    // Add more test cases to cover edge cases, invalid inputs, and other scenarios
}
