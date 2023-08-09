package com.movie.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.domain.model.User;
import com.movie.api.domain.request.UserRequest;
import com.movie.api.domain.response.UserResponse;
import com.movie.api.service.AuthenticationService;
import com.movie.api.service.UserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private UserDetailService userDetailService;

    @InjectMocks
    private UserController authenticationController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCreateUser() {
        UserRequest userRequest = new UserRequest();
        // Set up userRequest

        User user = new User();
        // Set up user based on userRequest

        when(userDetailService.createUserDetails(any(User.class))).thenReturn(user);

        ResponseEntity<UserResponse> expectedResponse = ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.builder().email(user.getEmail()).message("Account created successfully").build());

        ResponseEntity<UserResponse> result = authenticationController.createUser(userRequest);

        // Assert result, HttpStatus, and any other assertions
    }

    // Add more test cases to cover edge cases, invalid inputs, and other scenarios
}
