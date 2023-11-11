package com.hackathoon.datavisualizer.security.mapper;

import com.hackathoon.datavisualizer.security.data.entity.AuthUserEntity;
import com.hackathoon.datavisualizer.security.dto.SignUpRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    AuthUserEntity requestToEntity(SignUpRequest signUpRequest);

}
