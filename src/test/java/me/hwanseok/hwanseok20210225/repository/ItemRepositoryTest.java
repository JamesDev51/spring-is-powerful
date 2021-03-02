package me.hwanseok.hwanseok20210225.repository;


import me.hwanseok.hwanseok20210225.ApplicationTest;
import me.hwanseok.hwanseok20210225.model.entity.Item;
import me.hwanseok.hwanseok20210225.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends ApplicationTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void create(){
        String status = "REGISTERED";
        String name = "노트북";
        String title ="컴퓨터-전자제품1호점의 가전제품 32호";
        String content = "테스트 content";
        Integer price = 1000;
        Long partnerId = 1L;

        Item item = new Item();
        item.setStatus(status);
        item.setName(name);
        item.setTitle(title);
        item.setContent(content);
        item.setPrice(price);
        // TODO FK 설정
//        item.setPartnerId(partnerId);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Optional<Item> item  = itemRepository.findById(1L);

        item.ifPresent(selectedItem -> {
            System.out.println("item :"+selectedItem);
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<Item> item = itemRepository.findById(1L);

        item.ifPresent(selectedItem -> {
            selectedItem.setPrice(9999);
            itemRepository.save(selectedItem);
            System.out.println("item :"+selectedItem);
        });

    }
    
    @Test
    @Transactional
    public void delete(){
        Optional<Item> item = itemRepository.findById(1L);

        Assertions.assertTrue(item.isPresent());    // false

        item.ifPresent(selectItem->{
            itemRepository.delete(selectItem);
        });
        
        Optional<Item> deleteItem = itemRepository.findById(1L);

        Assertions.assertFalse(deleteItem.isPresent());
    }
}
