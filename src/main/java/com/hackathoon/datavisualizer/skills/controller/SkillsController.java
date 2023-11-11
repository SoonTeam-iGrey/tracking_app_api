package com.hackathoon.datavisualizer.skills.controller;

import com.hackathoon.datavisualizer.skills.data.model.request.AddSkillsTrackingEntryRequest;
import com.hackathoon.datavisualizer.skills.data.model.response.AddedSkillsTrackingEntryResponse;
import com.hackathoon.datavisualizer.skills.service.SkillsTrackingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.licenta.datavisualizer.Mappings.SKILLS;

@RestController
@AllArgsConstructor
@RequestMapping(SKILLS)
@CrossOrigin(originPatterns = "*", maxAge = -1)
public class SkillsController {

    private final SkillsTrackingService skillsTrackingService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<AddedSkillsTrackingEntryResponse> getMySkillsTrack(Principal principal) {
        return skillsTrackingService.getMySkillsTracking(principal.getName());
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public List<AddedSkillsTrackingEntryResponse> getMyDomainTrack(Principal principal,
                                                                   @Valid @RequestBody List<AddSkillsTrackingEntryRequest> skillsToAdd) {
        return skillsTrackingService.addSkillsTracking(principal.getName(), skillsToAdd);
    }

}
