package com.hackathoon.datavisualizer.skills.data.mapper;

import com.hackathoon.datavisualizer.domains.data.entity.WorkDomainEntity;
import com.hackathoon.datavisualizer.domains.data.repository.WorkDomainRepository;
import com.hackathoon.datavisualizer.security.data.entity.AuthUserEntity;
import com.hackathoon.datavisualizer.skills.data.entity.SkillsTrackingEntity;
import com.hackathoon.datavisualizer.skills.data.model.request.AddSkillsTrackingEntryRequest;
import com.hackathoon.datavisualizer.skills.data.model.response.AddedSkillsTrackingEntryResponse;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SkillsTrackerMapper {

    private static final AddSkillsTrackingEntryRequest DEFAULT_ADD_REQUEST = new AddSkillsTrackingEntryRequest();

    @Autowired
    private WorkDomainRepository workDomainRepository;

    public List<SkillsTrackingEntity> createSkillsTrackingEntities(AuthUserEntity user, List<AddSkillsTrackingEntryRequest> skillsToAdd) {
        final LocalDateTime now = LocalDateTime.now();
        final Map<String, AddSkillsTrackingEntryRequest> map = skillsToAdd.stream()
                .collect(Collectors.toMap(AddSkillsTrackingEntryRequest::getDomain, Function.identity(), (e1, e2) -> e1));
        return workDomainRepository.findAll().stream()
                .map(workDomainEntity -> {
                    AddSkillsTrackingEntryRequest skill = map.getOrDefault(workDomainEntity.getName(), DEFAULT_ADD_REQUEST);
                    return SkillsTrackingEntity.builder()
                            .domain(workDomainEntity)
                            .practicalScore(skill.getPracticalScore())
                            .theoreticalScore(skill.getTheoreticalScore())
                            .experienceInMonths(skill.getExperienceInMonths())
                            .moment(now)
                            .user(user)
                            .build();
                })
                .toList();
    }

    @Mapping(source = "domain.name", target = "domain")
    public abstract AddedSkillsTrackingEntryResponse entityToResponse(SkillsTrackingEntity entity);

}
