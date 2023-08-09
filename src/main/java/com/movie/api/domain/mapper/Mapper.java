package com.movie.api.domain.mapper;

public interface Mapper<T, U> {

    public U mapToDTO(T source);


}
