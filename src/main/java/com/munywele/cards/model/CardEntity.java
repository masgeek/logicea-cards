package com.munywele.cards.model;

import com.munywele.cards.enums.EnumCardStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cards", schema = "cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 50)
    @NotNull
    @Column(name = "card_name", nullable = false, length = 50)
    private String cardName;

    @Size(max = 8)
    @Column(name = "card_color", length = 8)
    private String cardColor;

    @NotNull
    @Column(name = "card_status", columnDefinition = "TEXT", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private EnumCardStatus cardStatus;

    @Column(name = "card_description", columnDefinition = "TEXT")
    private String cardDescription;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    //Begin the getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public EnumCardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(EnumCardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
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
