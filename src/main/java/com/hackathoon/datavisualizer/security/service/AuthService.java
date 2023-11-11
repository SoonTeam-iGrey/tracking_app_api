package com.hackathoon.datavisualizer.security.service;

import com.hackathoon.datavisualizer.exception.BadAuthCredentialsException;
import com.hackathoon.datavisualizer.exception.UserAlreadyExistingExceptionWeb;
import com.hackathoon.datavisualizer.security.data.entity.AuthUserEntity;
import com.hackathoon.datavisualizer.security.data.repository.AuthUserRepository;
import com.hackathoon.datavisualizer.security.data.repository.RolesRepository;
import com.hackathoon.datavisualizer.security.details.UserDetailsImpl;
import com.hackathoon.datavisualizer.security.dto.JwtResponse;
import com.hackathoon.datavisualizer.security.dto.LoginRequest;
import com.hackathoon.datavisualizer.security.dto.SignUpRequest;
import com.hackathoon.datavisualizer.security.jwt.JwtUtils;
import com.hackathoon.datavisualizer.security.mapper.AuthUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtUtils jwtUtils;
    private AuthUserMapper authUserMapper;
    private final RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final AuthenticationManager authenticationManager;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        checkCredentials(loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .roles(roles)
                .expirationTime(jwtUtils.getJwtExpirationMs())
                .build();
    }


    public JwtResponse signUp(SignUpRequest signUpRequest) {
        checkAccountCreation(signUpRequest);

        AuthUserEntity toSave = authUserMapper.requestToEntity(signUpRequest);
        toSave.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword()));
        toSave.setRoles(rolesRepository.getDefaultRoles());
        authUserRepository.save(toSave);

        return authenticateUser(LoginRequest.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .build());
    }

    private void checkAccountCreation(SignUpRequest signUpRequest) {
        if (authUserRepository.existsByUsername(signUpRequest.getUsername()) ||
                authUserRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            throw new UserAlreadyExistingExceptionWeb("The username or phone number is already taken.");
        }
    }

    private void checkCredentials(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String passwordHash = passwordEncoder.encode(loginRequest.getPassword());
        if (!authUserRepository.existsByUsernameAndPasswordHash(username, passwordHash)) {
            throw new BadAuthCredentialsException("The username or password are not valid.");
        }
    }

}
