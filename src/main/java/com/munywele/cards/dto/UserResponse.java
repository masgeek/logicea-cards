package com.munywele.cards.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.munywele.cards.enums.EnumUserRole;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class UserResponse {
    private String fullNames;
    private String userEmail;
    private EnumUserRole userRole;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public EnumUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(EnumUserRole userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
