package com.movie.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

import com.movie.api.domain.model.Rating;
import com.movie.api.repository.MovieRepository;
import com.movie.api.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private final EntityManager entityManager;

    public MovieService(EntityManager entityManager,MovieRepository movieRepository ) {
        this.entityManager = entityManager;
        this.movieRepository=movieRepository;
    }

    public Page<Movie> getMovies(Movie movie, Pageable pageable) {
        return movieRepository.findAll(getOscarMovie(movie),pageable);
    }

    public Movie getMovie(Long id) {
        if (id == null) {
            throw new NoSuchElementException("Person id is null");
        }
        return movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie not found!"));
    }

    public Movie saveMovie(Movie movie) {
        if (movie == null || movie.isEmpty()) {
            throw new IllegalArgumentException("Movie is invalid!");
        }
        return movieRepository.save(movie);
    }

    public Specification<Movie> getOscarMovie(Movie movie) {
        return (root, query, builder) -> {
            Predicate wonPredicate = builder.equal(root.get("won"), "YES");
            return builder.and(wonPredicate);
        };
    }

    @Transactional(readOnly = true)
    public List<Movie> getWinningMoviesWithAverageRating() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> movieRoot = query.from(Movie.class);
        Predicate wonPredicate = cb.equal(movieRoot.get("won"), "YES");
        Join<Movie, Rating> ratingJoin = movieRoot.join("ratings", JoinType.INNER);
        Expression<Number> avgRating = cb.quot(cb.sum(ratingJoin.get("rating")), cb.count(ratingJoin));

        query.select(movieRoot)
                .groupBy(movieRoot)
                .orderBy(cb.asc(avgRating))
                .where(wonPredicate);

        return entityManager.createQuery(query).getResultList();
    }




    public void deleteMovie(Movie movie) {
        if (movie == null || movie.getId() == null) {
            throw new IllegalArgumentException("Movie is invalid!");
        }
        movieRepository.delete(movie);
    }
}

