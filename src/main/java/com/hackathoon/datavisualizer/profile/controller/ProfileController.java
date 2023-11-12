package com.hackathoon.datavisualizer.profile.controller;

import com.hackathoon.datavisualizer.profile.data.dto.response.InterviewScheduleResponse;
import com.hackathoon.datavisualizer.profile.data.dto.response.ProfileResponse;
import com.hackathoon.datavisualizer.profile.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
        return profileService.getProfileByUsername(principal.getName());
    }

    @PostMapping("/schedule")
//    @PreAuthorize("hasAuthority('RECRUITER')")
    public List<InterviewScheduleResponse> scheduleToInterview(@RequestBody List<String> usernames) {
        return profileService.scheduleProfilesToInterview(usernames);
    }

}
