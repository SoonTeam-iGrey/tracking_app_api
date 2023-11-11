package com.hackathoon.datavisualizer.profile.data.dto.mapper;

import com.hackathoon.datavisualizer.profile.data.dto.response.ExperienceResponse;
import com.hackathoon.datavisualizer.profile.data.entity.ExperienceEntity;
import com.hackathoon.datavisualizer.profile.data.entity.SkillEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ExperienceMapper {

    @Mappings({
            @Mapping(target = "companyCode", source = "company.code"),
            @Mapping(target = "companyName", source = "company.name"),
            @Mapping(target = "skills", expression = "java(mapExperience(entity))"),
    })
    public abstract ExperienceResponse entityToDto(ExperienceEntity entity);

    public List<String> mapExperience(ExperienceEntity entity) {
        return entity.getSkills().stream().map(SkillEntity::getName).toList();
    }

}
