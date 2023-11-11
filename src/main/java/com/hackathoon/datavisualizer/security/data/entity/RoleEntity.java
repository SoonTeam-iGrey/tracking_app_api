package com.hackathoon.datavisualizer.security.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "role", fetch = LAZY)
    private Set<RoleMappingEntity> roleMappings;

}