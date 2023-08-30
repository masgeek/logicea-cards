package com.munywele.cards.repository;

import com.munywele.cards.model.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @NotNull
    Optional<UserEntity> findById(@NotNull Long userId);
    Optional<UserEntity> findByUserEmail(String userEmail);

}
