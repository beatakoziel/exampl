package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserPrincipal, Long> {
    Optional<User> findByUsername(String username);
}
