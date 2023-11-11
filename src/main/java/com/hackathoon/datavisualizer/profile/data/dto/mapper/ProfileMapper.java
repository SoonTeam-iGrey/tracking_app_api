package com.hackathoon.datavisualizer.profile.data.dto.mapper;

import com.hackathoon.datavisualizer.profile.data.dto.response.ProfileResponse;
import com.hackathoon.datavisualizer.profile.data.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProfileMapper {

    @Autowired
    private ExperienceMapper experienceMapper;

    @Mapping(target = "experience", expression = "java(entity.getExperience().stream().map(e -> getExperienceMapper().entityToDto(e)).toList())")
    public abstract ProfileResponse entityToDto(ProfileEntity entity);

    public ExperienceMapper getExperienceMapper() {
        return experienceMapper;
    }

}
