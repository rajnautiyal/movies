package com.movie.api.service;

import com.movie.api.domain.dto.MovieDTO;
import com.movie.api.domain.mapper.MovieMapper;
import com.movie.api.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MovieFacade {

    private final OMDBService omdbApiService;
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieFacade(
            OMDBService omdbApiService,
            MovieService movieService,
            MovieMapper movieMapper) {
        this.omdbApiService = omdbApiService;
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    public Page<MovieDTO> getMovieData(Movie movie, Pageable pageable) {
        Movie OmdbMovie = omdbApiService.getMovieDetails(movie.getName());
        Page<Movie> moviePages;
        if(OmdbMovie!=null){
            moviePages = movieService.getMovies(OmdbMovie, pageable);
        }else{
            moviePages = movieService.getMovies(movie, pageable);
        }
        Page<MovieDTO> mappedOscarMoviesPage = moviePages.map(movieMapper::mapToDTO);
        List<Movie> resultList=movieService.getWinningMoviesWithAverageRating();
        for (Movie resultMovie : resultList) {
            System.out.println("Movie Name: " + resultMovie.getName());
            // Print other attributes as needed
        }
        return mappedOscarMoviesPage;
    }



}