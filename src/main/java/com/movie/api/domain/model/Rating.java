package com.movie.api.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@Getter
@Setter
public class Rating {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(name = "count")
        private Double rating;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "movie_id", nullable = false)
        private Movie movie;

    }

