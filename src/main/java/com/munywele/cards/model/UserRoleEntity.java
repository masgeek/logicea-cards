package com.munywele.cards.model;

import com.munywele.cards.enums.EnumUserRole;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {
    @Id
    @Column(name = "role_name", columnDefinition = "TEXT", nullable = false, length = 11)
    @Enumerated(EnumType.STRING)
    private EnumUserRole roleName;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public EnumUserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(EnumUserRole roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
