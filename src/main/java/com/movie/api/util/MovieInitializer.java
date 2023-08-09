package com.movie.api.util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.InputStream;


import java.io.InputStreamReader;
import com.movie.api.domain.model.Movie;
@Component
public class MovieInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    @Async
    public void run(String... args) throws Exception {
        long movieCount = entityManager.createQuery("SELECT COUNT(m) FROM Movie m", Long.class).getSingleResult();

        if (movieCount == 0) {
            initializeMovies();
        }
    }

    private void initializeMovies() {
        try (InputStream inputStream = getClass().getResourceAsStream("/academy_awards.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }

                String[] values = line.split(",");
                if (values.length == 5) {
                    Movie newMovie = new Movie();
                    newMovie.setYear(values[0]);
                    newMovie.setCategory(values[1]);
                    newMovie.setNominee(values[2]);
                   newMovie.setAdditionalInfo(values[3]);
                    setMovieNameFromAdditionalInfo(newMovie);
                    newMovie.setWon(values[4]);
                    entityManager.persist(newMovie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMovieNameFromAdditionalInfo(Movie movie) {
        if (movie.getAdditionalInfo() != null) {
            int openingBraceIndex = movie.getAdditionalInfo().indexOf('{');

            if (openingBraceIndex != -1) {
                String extractedName = movie.getAdditionalInfo().substring(0, openingBraceIndex).trim();
                movie.setName(extractedName);
            }
        }
    }
}
