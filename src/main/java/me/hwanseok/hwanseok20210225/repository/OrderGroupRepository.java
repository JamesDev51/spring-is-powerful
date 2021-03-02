package me.hwanseok.hwanseok20210225.repository;

import me.hwanseok.hwanseok20210225.model.entity.Item;
import me.hwanseok.hwanseok20210225.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
}
