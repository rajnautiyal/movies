package com.movie.api.domain.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MovieQuery extends Query {

    private @Getter @Setter String name;
    private @Getter @Setter Integer year;



}
