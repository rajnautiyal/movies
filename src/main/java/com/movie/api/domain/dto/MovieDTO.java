package com.movie.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Data
public class MovieDTO {

    private  String name;
    private  String category;
    private  String additionInfo;
    private  String year;
    private String won;

}
