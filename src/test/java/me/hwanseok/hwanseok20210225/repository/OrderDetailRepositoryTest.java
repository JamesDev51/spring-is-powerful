package me.hwanseok.hwanseok20210225.repository;


import me.hwanseok.hwanseok20210225.ApplicationTest;
import me.hwanseok.hwanseok20210225.model.entity.OrderDetail;
import me.hwanseok.hwanseok20210225.model.entity.User;
import org.apache.tomcat.jni.Local;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends ApplicationTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        String status = "REGISTERED";
        LocalDateTime arrivalDate = LocalDateTime.now().plusDays(2);
        Long orderGroupId = 1L;
        Long itemId = 1L;
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(status);
        orderDetail.setArrivalDate(arrivalDate);
        // TODO 연관관계 설정
//        orderDetail.setOrderGroupId(orderGroupId);
//        orderDetail.setItemId(itemId);
        orderDetail.setCreatedAt(createdAt);
        orderDetail.setCreatedBy(createdBy);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);

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

        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);

        Assertions.assertNotNull(orderDetail);
    }

    @Test
    @Transactional
    public void update() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);

        orderDetail.ifPresent(selectedOrderDetail -> {
            // TODO 연관관계 포함된 test code 작성
//            selectedOrderDetail.setOrderGroupId(14L);
            OrderDetail updatedOrderDetail = orderDetailRepository.save(selectedOrderDetail);
//            Assertions.assertEquals(updatedOrderDetail.getOrderGroupId(), 14L);
        });

    }

    @Test
    @Transactional
    public void delete() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);

        Assertions.assertTrue(orderDetail.isPresent());    // false

        orderDetail.ifPresent(selectUser -> {
            orderDetailRepository.delete(selectUser);
        });

        Optional<OrderDetail> deleteOrderDetail = orderDetailRepository.findById(1L);

        Assertions.assertFalse(deleteOrderDetail.isPresent());
    }
}
