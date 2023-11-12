package com.hackathoon.datavisualizer.profile.data.dto.response;

import java.util.List;

public record LatestRadarSkillsResponse(
        List<String> domains,
        List<Double> values
) {
}
