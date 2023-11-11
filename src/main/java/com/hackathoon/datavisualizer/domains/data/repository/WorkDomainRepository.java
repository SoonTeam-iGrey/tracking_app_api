package com.hackathoon.datavisualizer.domains.data.repository;

import com.hackathoon.datavisualizer.domains.data.entity.WorkDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDomainRepository extends JpaRepository<WorkDomainEntity, Integer> {
}
