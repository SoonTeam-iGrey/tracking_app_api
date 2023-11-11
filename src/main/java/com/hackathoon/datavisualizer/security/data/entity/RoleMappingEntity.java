package com.hackathoon.datavisualizer.security.data.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles_mapping")
public class RoleMappingEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "user_id")
    private AuthUserEntity user;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public String getRoleName() {
        return role.getName();
    }

}