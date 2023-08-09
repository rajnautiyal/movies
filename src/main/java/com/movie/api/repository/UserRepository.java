package com.movie.api.repository;

import com.movie.api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, String> {
    User findFirstUserByEmail(String email);


}
