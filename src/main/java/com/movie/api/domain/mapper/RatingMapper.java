package com.movie.api.domain.mapper;

import com.movie.api.domain.dto.RatingDTO;
import com.movie.api.domain.model.Rating;
import com.movie.api.domain.request.RatingRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Service
public class RatingMapper implements Mapper<Rating,RatingDTO> {

    public Rating mapToEntity(@Valid RatingRequest request) throws NoSuchElementException {
        Rating rating = new Rating();
        rating.setRating(request.getRating());
        return rating;
    }

    @Override
    public RatingDTO mapToDTO(Rating source) {
        RatingDTO ratingDto = new RatingDTO();
        ratingDto.setRating(source.getRating());
        ratingDto.setId(source.getId());
        return ratingDto;
    }
}
