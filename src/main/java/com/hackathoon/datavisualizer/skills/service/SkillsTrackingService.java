package com.hackathoon.datavisualizer.skills.service;

import com.hackathoon.datavisualizer.domains.data.entity.WorkDomainEntity;
import com.hackathoon.datavisualizer.domains.data.repository.WorkDomainRepository;
import com.hackathoon.datavisualizer.domains.service.WorkDomainService;
import com.hackathoon.datavisualizer.security.data.entity.AuthUserEntity;
import com.hackathoon.datavisualizer.security.data.repository.AuthUserRepository;
import com.hackathoon.datavisualizer.security.service.UserService;
import com.hackathoon.datavisualizer.skills.data.entity.SkillsTrackingEntity;
import com.hackathoon.datavisualizer.skills.data.mapper.SkillsTrackerMapper;
import com.hackathoon.datavisualizer.skills.data.model.request.AddSkillsTrackingEntryRequest;
import com.hackathoon.datavisualizer.skills.data.model.response.AddedSkillsTrackingEntryResponse;
import com.hackathoon.datavisualizer.skills.data.repository.SkillsTrackingRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SkillsTrackingService {

    private final UserService userService;
    private final SkillsTrackerMapper skillsTrackerMapper;
    private final SkillsTrackingRepository skillsTrackingRepository;

    public List<AddedSkillsTrackingEntryResponse> getMySkillsTracking(String username) {
        return skillsTrackingRepository.getUserSkillsTracking(username).stream()
                .map(skillsTrackerMapper::entityToResponse)
                .toList();
    }

    public List<AddedSkillsTrackingEntryResponse> addSkillsTracking(String username, List<AddSkillsTrackingEntryRequest> skillsToAdd) {
        AuthUserEntity user = userService.getByUsername(username);

        List<SkillsTrackingEntity> toPersist = skillsTrackerMapper.createSkillsTrackingEntities(user, skillsToAdd);
        skillsTrackingRepository.saveAll(toPersist);

        return toPersist.stream()
                .map(skillsTrackerMapper::entityToResponse)
                .toList();
    }

}
