package com.example.demojwt.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface Repository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
