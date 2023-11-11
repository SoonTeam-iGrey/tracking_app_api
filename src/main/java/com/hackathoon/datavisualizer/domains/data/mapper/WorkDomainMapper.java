package com.hackathoon.datavisualizer.domains.data.mapper;

import com.hackathoon.datavisualizer.domains.data.entity.WorkDomainEntity;
import com.hackathoon.datavisualizer.domains.data.model.response.WorkDomainResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkDomainMapper {

    WorkDomainResponse entityToDto (WorkDomainEntity entity);

}
