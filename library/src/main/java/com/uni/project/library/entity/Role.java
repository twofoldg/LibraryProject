package com.uni.project.library.entity;

import com.uni.project.library.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serial;

@Entity
@Table(name = "roles")
@Immutable
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 2032361808860194306L;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
