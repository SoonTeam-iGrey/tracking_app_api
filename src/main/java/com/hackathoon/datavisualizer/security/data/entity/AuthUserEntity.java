package com.hackathoon.datavisualizer.security.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class AuthUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "username")
    private String username;
    @Column(name = "password_hash")
    private String passwordHash;

    @OneToMany(mappedBy = "user", fetch = EAGER, cascade = ALL)
    private Set<RoleMappingEntity> roles;

    public Set<RoleEntity> getRoles() {
        return roles.stream()
                .map(RoleMappingEntity::getRole)
                .collect(Collectors.toSet());
    }

    public List<String> getRoleNames() {
        return getRoles().stream()
                .map(RoleEntity::getName)
                .toList();
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles.stream()
                .map(role -> RoleMappingEntity.builder()
                        .user(this)
                        .role(role)
                        .build())
                .collect(Collectors.toSet());
    }

}