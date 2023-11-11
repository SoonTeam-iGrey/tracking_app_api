package com.hackathoon.datavisualizer.security.controller;

import com.hackathoon.datavisualizer.security.details.UserDetailsImpl;
import com.hackathoon.datavisualizer.security.dto.JwtResponse;
import com.hackathoon.datavisualizer.security.dto.LoginRequest;
import com.hackathoon.datavisualizer.security.dto.SignUpRequest;
import com.hackathoon.datavisualizer.security.jwt.JwtUtils;
import com.hackathoon.datavisualizer.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import java.util.List;

import static com.licenta.datavisualizer.Mappings.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    @PermitAll
    @PreAuthorize("permitAll()")
    @PostMapping({SIGN_IN, LOGIN})
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PermitAll
    @PreAuthorize("permitAll()")
    @PostMapping({SIGN_UP})
    public JwtResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }

}