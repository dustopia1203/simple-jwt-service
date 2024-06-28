package com.example.auth.repository;

import com.example.auth.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
        SELECT t 
        FROM Token t 
        WHERE t.username = :username AND t.isRevoked = FALSE 
    """)
    List<Token> findAllValidToken(String username);

    Optional<Token> findByToken(String token);

}
