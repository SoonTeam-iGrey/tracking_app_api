package com.hackathoon.datavisualizer.skills.data.repository;

import com.hackathoon.datavisualizer.skills.data.entity.SkillsTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillsTrackingRepository extends JpaRepository<SkillsTrackingEntity, Long> {

    @Query("""
        SELECT ste
        FROM SkillsTrackingEntity ste
        WHERE ste.user.username = :username
        """)
    List<SkillsTrackingEntity> getUserSkillsTracking(String username);

}
