package com.movie.api.service;


import com.movie.api.domain.model.Movie;
import com.movie.api.domain.model.Rating;
import com.movie.api.repository.MovieRepository;
import com.movie.api.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingService{

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    @Transactional
    public Rating createRatingForMovie(Long movieId, Rating rating) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        rating.setMovie(movie);
        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsForMovie(Long movieId) {
        return ratingRepository.findByMovieId(movieId);
    }
}
