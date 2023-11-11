package com.hackathoon.datavisualizer.skills.data.entity;

import com.hackathoon.datavisualizer.domains.data.entity.WorkDomainEntity;
import com.hackathoon.datavisualizer.security.data.entity.AuthUserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_skills_tracking")
public class SkillsTrackingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUserEntity user;

    @Column(name = "timestamp")
    private LocalDateTime moment;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private WorkDomainEntity domain;

    @Column(name = "domain_score_theoretical")
    private Double theoreticalScore;
    @Column(name = "domain_score_practical")
    private Double practicalScore;
    @Column(name = "experience_months")
    private Integer experienceInMonths;

}
