package com.munywele.cards.repository;

import com.munywele.cards.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
