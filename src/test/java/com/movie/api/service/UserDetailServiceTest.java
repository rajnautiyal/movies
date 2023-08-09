package com.movie.api.service;

import com.movie.api.domain.model.User;
import com.movie.api.repository.UserRepository;
import com.movie.api.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UserDetailService userDetailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername_ValidUser() {
        String email = "test@example.com";
        String password = "testPassword";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.findFirstUserByEmail(email)).thenReturn(user);

        UserDetails userDetails = userDetailService.loadUserByUsername(email);

        // Assert userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
        // Perform necessary assertions
    }

    @Test
    public void testLoadUserByUsername_InvalidUser() {
        String email = "nonexistent@example.com";

        when(userRepository.findFirstUserByEmail(email)).thenReturn(null);

        // Assert that an exception is thrown
        // Example: assertThrows(UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername(email));
    }

    @Test
    public void testCreateUserDetails() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("testPassword");

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));

        when(jwtTokenUtil.generateToken(any(UserDetails.class))).thenReturn("testToken");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userDetailService.createUserDetails(user);

        // Assert savedUser, savedUser.getPassword(), savedUser.getToken()
        // Perform necessary assertions
    }

    // Add more test cases to cover edge cases, invalid inputs, and other scenarios
}
