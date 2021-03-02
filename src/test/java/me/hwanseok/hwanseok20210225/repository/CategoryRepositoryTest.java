package me.hwanseok.hwanseok20210225.repository;


import me.hwanseok.hwanseok20210225.ApplicationTest;
import me.hwanseok.hwanseok20210225.model.entity.Category;
import me.hwanseok.hwanseok20210225.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryTest extends ApplicationTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "Admin";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);
        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(), type);
        Assertions.assertEquals(newCategory.getTitle(), title);
    }

    @Test
    public void read(){
        Long id = 5L;
        String type = "FOOD";
        Optional<List<Category>> category = categoryRepository.findByType(type);

        Assertions.assertNotNull(category);

        category.ifPresent(cateList -> {
            cateList.forEach(cate -> {
                Assertions.assertEquals(cate.getType(), type);
                System.out.println(cate.getTitle());
            });
        });
    }

    @Test
    @Transactional
    public void update(){
        Long id = 5L;
        String type = "COMPUTER";
        String newTitle = "New Title";
        Optional<List<Category>> category = categoryRepository.findByType(type);

        Assertions.assertNotNull(category);

        category.ifPresent(cateList -> {
            cateList.forEach(cate -> {
                cate.setTitle(newTitle);
                Category savedCate = categoryRepository.save(cate);
                Assertions.assertEquals(savedCate.getTitle(), newTitle);
            });
        });
    }
    
    @Test
    @Transactional
    public void delete(){
        Long id = 5L;
        String type = "COMPUTER";
        String newTitle = "New Title";
        Optional<List<Category>> category = categoryRepository.findByType(type);

        Assertions.assertNotNull(category);

        category.ifPresent(cateList -> {
            cateList.forEach(cate -> {
                categoryRepository.delete(cate);
            });
        });
        Optional<List<Category>> updatedCategory = categoryRepository.findByType(type);
        Assertions.assertNotNull(updatedCategory);
        Assertions.assertTrue(updatedCategory.get().isEmpty());
    }
}
