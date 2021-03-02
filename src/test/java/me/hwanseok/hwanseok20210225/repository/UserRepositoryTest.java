package me.hwanseok.hwanseok20210225.repository;


import me.hwanseok.hwanseok20210225.ApplicationTest;
import me.hwanseok.hwanseok20210225.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.SaslServer;
import javax.transaction.TransactionScoped;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends ApplicationTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void create(){
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1111-3333";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);

    }

    @Test
    public void read(){
        String account = "Test02";
        String password = "Test02";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Optional<User> user = userRepository.findByPhoneNumberOrderByIdDesc(phoneNumber);

        Assertions.assertNotNull(user);
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectedUser -> {
            selectedUser.setEmail("ppp");
            userRepository.save(selectedUser);
            System.out.println("user :"+selectedUser);

        });

    }
    
    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        Assertions.assertTrue(user.isPresent());    // false

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });
        
        Optional<User> deleteUser = userRepository.findById(1L);

        Assertions.assertFalse(deleteUser.isPresent());
    }

    @Test
    @Transactional 
    public void readOrderInfo(){
        String account = "Test02";
        String password = "Test02";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Long id = 1L;
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(selectedUser -> {
            selectedUser.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-----------주문 그룹 ------------");
                System.out.println("총 주문 가격" + orderGroup.getTotalPrice());
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("-----------개별 주문 ------------");
                    System.out.println("주문 상품"+ orderDetail.getItem().getName());
                    System.out.println("파트너"+ orderDetail.getItem().getPartner().getName());
                    System.out.println("카테고리"+ orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("개별 주문 가격"+orderDetail.getTotalPrice());
                });
            });
        });
        Assertions.assertNotNull(user);
    }
}
