package me.hwanseok.hwanseok20210225.repository;


import me.hwanseok.hwanseok20210225.ApplicationTest;
import me.hwanseok.hwanseok20210225.model.entity.AdminUser;
import me.hwanseok.hwanseok20210225.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class AdminUserRepositoryTest extends ApplicationTest {

    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String role = "ALL";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        AdminUser adminUser = new AdminUser();
        adminUser.setAccount(account);
        adminUser.setPassword(password);
        adminUser.setStatus(status);
        adminUser.setRole(role);
        adminUser.setCreatedAt(createdAt);
        adminUser.setCreatedBy(createdBy);

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);

    }

    @Test
    public void read(){
        Long id = 1L;
        Optional<AdminUser> adminUser = adminUserRepository.findById(id);
        Assertions.assertNotNull(adminUser);
    }

    @Test
    @Transactional
    public void update(){
        Long id = 1L;
        Optional<AdminUser> adminUser = adminUserRepository.findById(id);
        String role = "CRUD";
        adminUser.ifPresent(selectedAdminUser -> {
            selectedAdminUser.setRole(role);
            AdminUser updatedAdminUser = adminUserRepository.save(selectedAdminUser);
            Assertions.assertNotNull(updatedAdminUser);
            Assertions.assertEquals(updatedAdminUser.getRole(), role);
        });
    }
    
    @Test
    @Transactional
    public void delete(){
        Long id = 1L;
        Optional<AdminUser> adminUser = adminUserRepository.findById(id);

        Assertions.assertTrue(adminUser.isPresent());    // false

        adminUser.ifPresent(selectAdminUser->{
            adminUserRepository.delete(selectAdminUser);
        });
        
        Optional<AdminUser> deleteAdminUser = adminUserRepository.findById(id);

        Assertions.assertFalse(deleteAdminUser.isPresent());
    }
}
                 