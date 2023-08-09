package com.movie.api.domain.mapper;

import java.util.NoSuchElementException;
import javax.validation.Valid;

import com.movie.api.domain.model.Movie;
import com.movie.api.domain.dto.MovieDTO;
import com.movie.api.domain.query.MovieQuery;
import com.movie.api.domain.request.MovieRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper implements Mapper<Movie, MovieDTO> {

    @Override
    public MovieDTO mapToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setName(movie.getName());
        movieDTO.setCategory(movie.getCategory());
         movieDTO.setAdditionInfo(movie.getAdditionalInfo());
        movieDTO.setWon(movie.getWon());
        if(movie.getAwards()!=null)
            movieDTO.setWon("Yes");
        return movieDTO;
    }

    public Movie mapToEntity(@Valid MovieRequest request) throws NoSuchElementException {
        Movie movie = new Movie();
        movie.setName(request.getName());
        return movie;
    }

    public Movie mapToEntity(MovieQuery query) {
        Movie movie = new Movie();
        movie.setName(query.getName());
        return movie;
    }

}
