package com.munywele.cards.repository;

import com.munywele.cards.enums.EnumUserRole;
import com.munywele.cards.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, EnumUserRole> {
}
