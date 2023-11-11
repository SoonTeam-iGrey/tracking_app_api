package com.hackathoon.datavisualizer.profile.service;

import com.hackathoon.datavisualizer.exception.NoResourceFoundExceptionWeb;
import com.hackathoon.datavisualizer.profile.data.dto.mapper.ProfileMapper;
import com.hackathoon.datavisualizer.profile.data.dto.response.ProfileResponse;
import com.hackathoon.datavisualizer.profile.data.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    public ProfileResponse getMyProfile(String username) {
        return profileRepository.findByUsername(username)
                .map(profileMapper::entityToDto)
                .orElseThrow(() -> new NoResourceFoundExceptionWeb("No user found with given username: %s".formatted(username)));
    }

}
