package com.movie.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employee_id;
    @Column(name = "email")
    private String email;
    @Column(name = "firstName")
    private String first_name;
    @Column(name = "lastName")
    private String last_name;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

}
