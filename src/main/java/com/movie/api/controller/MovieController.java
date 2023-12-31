package com.movie.api.controller;

import java.util.NoSuchElementException;
import javax.validation.Valid;


import com.movie.api.service.MovieFacade;
import com.movie.api.service.MovieService;
import com.movie.api.util.Constants;

import com.movie.api.domain.mapper.MovieMapper;
import com.movie.api.domain.model.Movie;
import com.movie.api.domain.dto.MovieDTO;
import com.movie.api.domain.query.MovieQuery;
import com.movie.api.domain.request.MovieRequest;
import com.movie.api.domain.response.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/v1/movies")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private MovieService movieService;
    private MovieMapper movieMapper;

    private MovieFacade movieFacade;

    @GetMapping()
    @Operation(summary = "List movies", description = "Lists all movies paginated")
    public @ResponseBody Page<MovieDTO> getMovies(MovieQuery query) {
        int page = query.getPage() == null ? 0 : query.getPage();
        int size = query.getSize() == null ? Constants.DEFAULT_PAGE_SIZE : query.getSize();
        Movie movie = movieMapper.mapToEntity(query);
        Pageable pageable = PageRequest.of(page, size);
        return movieFacade.getMovieData(movie, pageable);
    }

    @GetMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Get movie", description = "Gets a movie information by id")
    public @ResponseBody ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        MovieDTO movieDTO = movieMapper.mapToDTO(movieService.getMovie(id));
        return ResponseEntity.ok().body(movieDTO);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Add movie", description = "Adds a new movie")
    public ResponseEntity<MovieDTO> saveMovie(@RequestBody @Valid MovieRequest request) {
        Movie movie = movieMapper.mapToEntity(request);
        movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieMapper.mapToDTO(movie));
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Update movie", description = "Updates a movie information by id")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id,
            @RequestBody @Valid MovieRequest request) {
        Movie movie = movieMapper.mapToEntity(request);
        movie.setId(id);
        movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.OK).body(movieMapper.mapToDTO(movie));
    }

    @DeleteMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Delete movie", description = "Deletes a movie by id")
    public ResponseEntity<ResponseMessage> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(movieService.getMovie(id));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(204, "Movie deleted", true));
    }


    // ----------------------------------------------------------------------------------
    // Exception handlers
    // ----------------------------------------------------------------------------------

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> handleNotFound(NoSuchElementException e) {
        ResponseMessage message = new ResponseMessage(404, e.getMessage(), false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ResponseMessage> handleBadRequest() {
        ResponseMessage message = new ResponseMessage(422, "Bad Request!", false);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
    }
}
