package com.hackathoon.datavisualizer.skills.data.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.hackathoon.datavisualizer.util.DateAndTimeUtil.dateTimePattern;

@Getter
@Setter
@NoArgsConstructor
public class AddedSkillsTrackingEntryResponse {

    private String domain;
    private Double theoreticalScore = 0.0;
    private Double practicalScore = 0.0;
    private Integer experienceInMonths = 0;
    @JsonFormat(pattern = dateTimePattern)
    private LocalDateTime moment;

}
