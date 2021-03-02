package me.hwanseok.hwanseok20210225.repository;

import me.hwanseok.hwanseok20210225.model.entity.Category;
import me.hwanseok.hwanseok20210225.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> findByType(String type);
}
