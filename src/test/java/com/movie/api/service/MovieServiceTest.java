package com.movie.api.service;

import com.movie.api.domain.model.Movie;
import com.movie.api.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMovieById() {
        Long movieId = 1L;
        Movie movie = new Movie();
        movie.setId(movieId);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovie(movieId);

        verify(movieRepository, times(1)).findById(movieId);
        // You can perform additional assertions on 'result' if needed
    }

    @Test
    public void testSaveValidMovie() {
        Movie movie = new Movie();
        movie.setName("Againing");
        movie.setYear("2023");

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        Movie result = movieService.saveMovie(movie);

        verify(movieRepository, times(1)).save(movie);
        // You can perform additional assertions on 'result' if needed
    }

    @Test
    public void testGetMoviesFromYearAndExample() {
        Movie exampleMovie = new Movie();
        exampleMovie.setWon("YES");
        exampleMovie.setName("Example Movie");

        // Mock the repository behavior
        Pageable pageable = mock(Pageable.class);
        Page<Movie> mockedPage = mock(Page.class);

        when(movieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(mockedPage);

        // Call the method being tested
        Page<Movie> result = movieService.getMovies(exampleMovie, pageable);

        // Verify the interactions
        verify(movieRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }


}
