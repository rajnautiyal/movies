package com.movie.api.controller;

import com.movie.api.domain.model.User;
import com.movie.api.domain.request.LoginRequest;
import com.movie.api.domain.request.UserRequest;


import com.movie.api.service.AuthenticationService;
import com.movie.api.service.UserDetailService;
import com.movie.api.domain.response.LoginResponse;
import com.movie.api.domain.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/v1/user")
@Tag(name = "User Controller", description = "Endpoints for user authentication")
public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());

    final AuthenticationService authenticationService;
    final UserDetailService userDetailsService;

    @PostMapping(path = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Login User", description = "Login User")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequestModel) {
        logger.info("logging module 1 ");
        return authenticationService.loginUser(loginRequestModel);
    }

    @PostMapping(path = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Register User", description = "Create a new user account.")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDetails, User.class);
        User userDetail = userDetailsService.createUserDetails(user);
       UserResponse userResponseModel =UserResponse.builder()
                .email(userDetail.getEmail()).message("Account created successfully").build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
    }


}

