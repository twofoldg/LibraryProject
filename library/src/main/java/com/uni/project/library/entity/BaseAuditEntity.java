package com.uni.project.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@MappedSuperclass
@Data
public class BaseAuditEntity extends BaseEntity{
    private static final long serialVersionUID = 7828349778822746102L;

    @NotNull
    @CreatedBy
    @Column(name = "user_id", updatable = false)
    public static User createdBy;

   @NotNull
   @CreatedDate
   @Column(name = "created_at", updatable = false)
   private OffsetDateTime createdAt;
}
