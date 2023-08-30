package com.munywele.cards.repository;

import com.munywele.cards.model.CardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    Page<CardEntity> findAllByUserId(Long userId, Pageable pageable);

    Optional<CardEntity> findByIdAndUserId(Long cardId, Long userId);
}
