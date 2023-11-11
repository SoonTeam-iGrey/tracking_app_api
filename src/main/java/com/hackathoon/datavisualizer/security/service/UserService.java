package com.hackathoon.datavisualizer.security.service;

import com.hackathoon.datavisualizer.exception.NoResourceFoundExceptionWeb;
import com.hackathoon.datavisualizer.security.data.entity.AuthUserEntity;
import com.hackathoon.datavisualizer.security.data.repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthUserRepository authUserRepository;

    public AuthUserEntity getByUsername(String username) {
        return authUserRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoResourceFoundExceptionWeb("No user found with username=%s".formatted(username)));
    }

}
