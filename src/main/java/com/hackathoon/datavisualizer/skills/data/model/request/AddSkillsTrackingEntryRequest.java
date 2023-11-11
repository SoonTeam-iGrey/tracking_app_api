package com.hackathoon.datavisualizer.skills.data.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AddSkillsTrackingEntryRequest {

    @NotBlank(message = "The domain name is mandatory.")
    private String domain;
    private Double theoreticalScore = 0.0;
    private Double practicalScore = 0.0;
    private Integer experienceInMonths = 0;

}
