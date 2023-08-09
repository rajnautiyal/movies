package com.movie.api.domain.request;

import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequest implements Request {

    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Movies under which category")
    private String category;
    @NotNull(message = "Date of events ")
    private String date;
    @NotNull(message = "additional information  date is required")
    private Date additionInfo;
    @NotNull(message = "Detail about movie won the oscar or not")
    private String won;

}
