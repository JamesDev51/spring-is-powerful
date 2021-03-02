package me.hwanseok.hwanseok20210225.repository;

import me.hwanseok.hwanseok20210225.model.entity.Item;
import me.hwanseok.hwanseok20210225.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Optional<Partner> findByAddress(String address);
}

