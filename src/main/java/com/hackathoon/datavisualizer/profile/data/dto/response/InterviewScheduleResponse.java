package com.hackathoon.datavisualizer.profile.data.dto.response;

public record InterviewScheduleResponse(
        ProfileResponse profile,
        LatestRadarSkillsResponse radarSkillsValue,
        Double totalScore
) {
}
