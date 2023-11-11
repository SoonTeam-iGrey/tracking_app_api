package com.hackathoon.datavisualizer.security.data.repository;

import com.hackathoon.datavisualizer.security.data.entity.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Integer> {

    Optional<AuthUserEntity> findUserByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsernameAndPasswordHash(String username, String passwordHash);

}
