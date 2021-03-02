package me.hwanseok.hwanseok20210225.repository;


import me.hwanseok.hwanseok20210225.ApplicationTest;
import me.hwanseok.hwanseok20210225.model.entity.OrderDetail;
import me.hwanseok.hwanseok20210225.model.entity.OrderGroup;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderGroupRepositoryTest extends ApplicationTest {

    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {
        String status = "REGISTERED";
        String orderType = "ALL";
        Long userId = 1L;

        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus(status);
        orderGroup.setOrderType(orderType);
//        orderGroup.setUserId(userId);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assertions.assertNotNull(newOrderGroup);
    }

    @Test
    public void read() {
        Long id = 1L;
        String status = "REGISTERED";
        LocalDateTime arrivalDate = LocalDateTime.now().plusDays(2);
        Long orderGroupId = 1L;
        Long itemId = 1L;
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(id);

        Assertions.assertNotNull(orderGroup);
    }

    @Test
    @Transactional
    public void update() {
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(1L);

        orderGroup.ifPresent(selectedOrderGroup -> {
            String orderType = "EACH";
            selectedOrderGroup.setOrderType(orderType);
            OrderGroup updatedOrderGroup = orderGroupRepository.save(selectedOrderGroup);
            Assertions.assertEquals(updatedOrderGroup.getOrderType(), orderType);
        });

    }

    @Test
    @Transactional
    public void delete() {
        Long id = 1L;
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(id);

        Assertions.assertTrue(orderGroup.isPresent());    // false

        orderGroup.ifPresent(item -> {
            orderGroupRepository.delete(item);
        });

        Optional<OrderGroup> deletedOrderGroup = orderGroupRepository.findById(1L);
        Assertions.assertFalse(deletedOrderGroup.isPresent());
    }
}
