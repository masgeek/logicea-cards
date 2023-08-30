package com.munywele.cards.repository;

import com.munywele.cards.model.UserEntity;
import com.munywele.cards.model.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {

    UserTokenEntity findByUserEmail(String userEmail);

}
