package com.hackathoon.datavisualizer.domains.controller;

import com.hackathoon.datavisualizer.domains.data.model.response.WorkDomainResponse;
import com.hackathoon.datavisualizer.domains.service.WorkDomainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.licenta.datavisualizer.Mappings.DOMAINS;

@RestController
@AllArgsConstructor
@RequestMapping(DOMAINS)
@CrossOrigin(maxAge = -1, originPatterns = "*")
public class WorkDomainController {

    private final WorkDomainService workDomainService;

    @GetMapping
    public List<WorkDomainResponse> getWorkDomains() {
        return workDomainService.getWorkDomains();
    }

}
