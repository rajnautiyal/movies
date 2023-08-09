package com.movie.api.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public  class UserResponse {
    private String email;
    private String message;






}
