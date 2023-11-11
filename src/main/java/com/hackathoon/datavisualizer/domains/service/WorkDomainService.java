package com.hackathoon.datavisualizer.domains.service;

import com.hackathoon.datavisualizer.domains.data.mapper.WorkDomainMapper;
import com.hackathoon.datavisualizer.domains.data.model.response.WorkDomainResponse;
import com.hackathoon.datavisualizer.domains.data.repository.WorkDomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkDomainService {

    private final WorkDomainMapper workDomainMapper;
    private final WorkDomainRepository workDomainRepository;

    public List<WorkDomainResponse> getWorkDomains() {
        return workDomainRepository.findAll().stream()
                .map(workDomainMapper::entityToDto)
                .toList();
    }

}
