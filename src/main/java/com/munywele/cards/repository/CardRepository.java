package com.munywele.cards.repository;

import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.model.CardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    Page<CardEntity> findAllByUserId(Long userId, Pageable pageable);

    @Query("SELECT c FROM CardEntity c WHERE " +
            "(c.userId = :userId) " +
            "AND (:cardName IS NULL OR c.cardName LIKE %:cardName%) " +
            "AND (:cardColor IS NULL OR c.cardColor LIKE %:cardColor%) " +
            "AND (:cardStatus IS NULL OR c.cardStatus = :cardStatus) " +
            "AND (:createdAt IS NULL OR c.createdAt >= :createdAt)")
    Page<CardEntity> searchCards(
            Long userId,
            String cardName,
            String cardColor,
            EnumCardStatus cardStatus,
            LocalDateTime createdAt,
            Pageable pageable
    );

    @Query("SELECT c FROM CardEntity c WHERE " +
            "(:cardName IS NULL OR c.cardName LIKE %:cardName%) " +
            "AND (:cardColor IS NULL OR c.cardColor LIKE %:cardColor%) " +
            "AND (:cardStatus IS NULL OR c.cardStatus = :cardStatus) " +
            "AND (:createdAt IS NULL OR c.createdAt >= :createdAt)")
    Page<CardEntity> searchCards(
            String cardName,
            String cardColor,
            EnumCardStatus cardStatus,
            LocalDateTime createdAt,
            Pageable pageable
    );

    Optional<CardEntity> findByIdAndUserId(Long cardId, Long userId);
}
