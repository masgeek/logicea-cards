package com.munywele.cards.repository;

import com.munywele.cards.enums.EnumUserRole;
import com.munywele.cards.model.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("junit")
@Rollback(false)
class UserEntityRoleRepositoryTest {

    @Autowired
    private UserRoleRepository repo;

    @Test
    public void testCreateRoles() {
        UserRoleEntity admin = new UserRoleEntity();
        admin.setRoleName(EnumUserRole.ADMIN);

        UserRoleEntity member = new UserRoleEntity();
        member.setRoleName(EnumUserRole.MEMBER);

        repo.saveAll(List.of(admin, member));
        long recordCount = repo.count();
        assertEquals(2, recordCount);
    }
}
