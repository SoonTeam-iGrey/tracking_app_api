package com.hackathoon.datavisualizer.profile.data.dto.response;

import java.util.List;

public record ProfileResponse(
        String firstName,
        String lastName,
        String username,
        String email,
        String phoneNumber,
        List<ExperienceResponse> experience
) {
}
