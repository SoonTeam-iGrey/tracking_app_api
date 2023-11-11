package com.hackathoon.datavisualizer.security.data.repository;

import com.hackathoon.datavisualizer.security.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface RolesRepository extends JpaRepository<RoleEntity, Integer> {

    @Query("""
        SELECT re
            FROM RoleEntity re
            WHERE re.name IN ('USER')
    """)
    Collection<RoleEntity> getDefaultRoles();

}
