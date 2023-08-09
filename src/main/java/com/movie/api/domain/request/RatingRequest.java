package com.movie.api.domain.request;
import lombok.Data;

@Data
public class RatingRequest {

    private long movieId;
    private double rating;

    // You can add validation annotations if needed
}
