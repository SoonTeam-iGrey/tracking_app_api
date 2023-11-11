package com.hackathoon.datavisualizer.profile.data.repository;

import com.hackathoon.datavisualizer.profile.data.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByUsername(String username);

}
