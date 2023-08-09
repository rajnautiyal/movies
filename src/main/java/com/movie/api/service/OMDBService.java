package com.movie.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.domain.model.Movie;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class OMDBService {
    protected final Log logger = LogFactory.getLog(getClass());
    @Value("${omdb.api.key}")
    private String apiKey;


    public Movie getMovieDetails(String movieTitle) {
        if(!StringUtils.isEmpty(movieTitle)) {
            RestTemplate restTemplate = new RestTemplate();

            String apiUrl = "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + movieTitle;
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Movie osdMovieDTO = objectMapper.readValue(jsonResponse, Movie.class);
                logger.info("movies Name from the IMDB : " + osdMovieDTO.getName() + "movies Year :  " + osdMovieDTO.getYear());
                return osdMovieDTO;
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } return  null;
    }
}
