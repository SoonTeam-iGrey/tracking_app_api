package com.hackathoon.datavisualizer.security.controller;

import com.hackathoon.datavisualizer.security.dto.JwtResponse;
import com.hackathoon.datavisualizer.security.dto.LoginRequest;
import com.hackathoon.datavisualizer.security.dto.SignUpRequest;
import com.hackathoon.datavisualizer.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import static com.hackathoon.datavisualizer.Mappings.*;

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