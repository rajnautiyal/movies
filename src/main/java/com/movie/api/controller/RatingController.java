package com.movie.api.controller;

import com.movie.api.domain.dto.RatingDTO;
import com.movie.api.domain.mapper.RatingMapper;
import com.movie.api.domain.model.Rating;
import com.movie.api.domain.request.RatingRequest;
import com.movie.api.domain.response.RatingResponse;
import com.movie.api.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/rating/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RatingController {

    private  RatingService ratingService;

    private RatingMapper ratingMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RatingResponse> createRatingForMovie(@RequestBody RatingRequest ratingRequest) {
        Rating rating = ratingMapper.mapToEntity(ratingRequest);
        RatingResponse ratingResponse=new RatingResponse();
        try {
            ratingService.createRatingForMovie(ratingRequest.getMovieId(), rating);
            ratingResponse.setMessage("thanks for rating");
        }catch (IllegalArgumentException exception){
            ratingResponse.setMessage("Movie not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingResponse);
    }

    @GetMapping(value = "movie/{movieId}")
    public ResponseEntity<List<RatingDTO>> getRatingsForMovie(@PathVariable Long movieId) {
        List<Rating> ratings = ratingService.getRatingsForMovie(movieId);
            return ResponseEntity.status(HttpStatus.CREATED).body(ratings.stream().map(ratingMapper::mapToDTO).collect(Collectors.toList()));

    }
}