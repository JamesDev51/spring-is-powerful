package me.hwanseok.hwanseok20210225.repository;

import me.hwanseok.hwanseok20210225.model.entity.AdminUser;
import me.hwanseok.hwanseok20210225.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
}
