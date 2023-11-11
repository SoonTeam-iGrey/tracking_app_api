package com.hackathoon.datavisualizer.profile.controller;

import com.hackathoon.datavisualizer.profile.data.dto.response.ProfileResponse;
import com.hackathoon.datavisualizer.profile.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.hackathoon.datavisualizer.Mappings.PROFILE;

@RestController
@AllArgsConstructor
@RequestMapping(PROFILE)
@CrossOrigin(originPatterns = "*", maxAge = -1)
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ProfileResponse getMyProfile(Principal principal) {
        return profileService.getMyProfile(principal.getName());
    }

}
