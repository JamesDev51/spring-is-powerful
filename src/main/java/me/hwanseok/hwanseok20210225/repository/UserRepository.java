package me.hwanseok.hwanseok20210225.repository;

import me.hwanseok.hwanseok20210225.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumberOrderByIdDesc(String phoneNumber);

}
