package com.movie.api.service;


import com.movie.api.repository.UserRepository;
import com.movie.api.util.JwtTokenUtil;
import com.movie.api.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {

    final UserRepository userRepository;
    final JwtTokenUtil jwtTokenUtil;


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstUserByEmail(email);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorityList);
    }

    public User createUserDetails(User user) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        String token = jwtTokenUtil.generateToken(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorityList));
       return  userRepository.save(user);


    }

}
