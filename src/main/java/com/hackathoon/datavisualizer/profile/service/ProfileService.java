package com.hackathoon.datavisualizer.profile.service;

import com.hackathoon.datavisualizer.exception.NoResourceFoundExceptionWeb;
import com.hackathoon.datavisualizer.profile.data.dto.mapper.ProfileMapper;
import com.hackathoon.datavisualizer.profile.data.dto.response.InterviewScheduleResponse;
import com.hackathoon.datavisualizer.profile.data.dto.response.LatestRadarSkillsResponse;
import com.hackathoon.datavisualizer.profile.data.dto.response.ProfileResponse;
import com.hackathoon.datavisualizer.profile.data.repository.ProfileRepository;
import com.hackathoon.datavisualizer.skills.data.model.response.AddedSkillsTrackingEntryResponse;
import com.hackathoon.datavisualizer.skills.service.SkillsTrackingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final SkillsTrackingService skillsTrackingService;

    public ProfileResponse getProfileByUsername(String username) {
        return profileRepository.findByUsername(username)
                .map(profileMapper::entityToDto)
                .orElseThrow(() -> new NoResourceFoundExceptionWeb("No user found with given username: %s".formatted(username)));
    }

    public List<InterviewScheduleResponse> scheduleProfilesToInterview(List<String> usernames) {
        return usernames.stream()
                .map(this::getInterviewScheduleResponseOfUser)
                .sorted(Comparator.comparing(InterviewScheduleResponse::totalScore).reversed())
                .toList();
    }

    private LatestRadarSkillsResponse getLatestSkills(String username) {
        List<AddedSkillsTrackingEntryResponse> latestSkills = skillsTrackingService.getUserLatestSkills2(username);
        List<String> domains = latestSkills.stream()
                .map(AddedSkillsTrackingEntryResponse::getDomain)
                .toList();
        List<Double> points = latestSkills.stream()
                .map(skill -> skill.getPracticalScore() + skill.getTheoreticalScore())
                .toList();
        return new LatestRadarSkillsResponse(domains, points);
    }

    private InterviewScheduleResponse getInterviewScheduleResponseOfUser(String username) {
        var profile = getProfileByUsername(username);
        var latestSkills = getLatestSkills(username);
        Double score = latestSkills.values().stream().reduce(Double::sum).orElse(0.0);
        return new InterviewScheduleResponse(profile, latestSkills, score);
    }

}
