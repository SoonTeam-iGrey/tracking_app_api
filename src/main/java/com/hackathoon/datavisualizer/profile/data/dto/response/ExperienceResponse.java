package com.hackathoon.datavisualizer.profile.data.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ExperienceResponse(
        LocalDate startDate,
        LocalDate endDate,
        String companyCode,
        String companyName,
        List<String> skills
) {
}
