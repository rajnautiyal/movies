package com.movie.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category")
    private String category;
    @JsonProperty("Title")
    @Column(name = "name")
    private String name;
    @JsonProperty("Year")
    @Column(name = "year")
    private String year;
    @JsonProperty("Actors")
    @Lob // Using @Lob for potentially larger data
    private String nominee;

    @JsonProperty("Type")
    @Lob
    private String additionalInfo;
    @Column(name = "won")
    private String won;
    @JsonProperty("Awards")
    String awards;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public String getWon() {
        if (this.won != null) {
            return this.won;
        }
        return null;
    }


    public boolean isEmpty() {
        return false;
    }

    public boolean getReleaseDate() {
        return false;
    }
}
