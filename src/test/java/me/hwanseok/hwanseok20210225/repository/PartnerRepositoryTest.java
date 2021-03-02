package me.hwanseok.hwanseok20210225.repository;


import me.hwanseok.hwanseok20210225.ApplicationTest;
import me.hwanseok.hwanseok20210225.model.entity.Partner;
import me.hwanseok.hwanseok20210225.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.IntSupplier;

public class PartnerRepositoryTest extends ApplicationTest {

    @Autowired
    PartnerRepository partnerRepository;

    @Test
    @Transactional
    public void create(){
        Long id;
        String name = "컴퓨터-전자제품10 호점";
        String status = "REGISTERED";
        String address="경기 시흥";
        String partnerNumber="010-7777-7777";
        String businessNumber="02-7777-7777";
        String ceoName="hwanseok";
        Long categoryId=1L;
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        // TODO 연관관계 설정
//        partner.setCategoryId(categoryId);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);

        Partner newPartner = partnerRepository.save(partner);
        Assertions.assertNotNull(newPartner);
    }

    @Test
    public void read(){
        Long id;
        String name = "컴퓨터-전자제품10 호점";
        String status = "REGISTERED";
        String address="경기 시흥";
        String partnerNumber="010-7777-7777";
        String businessNumber="02-7777-7777";
        String ceoName="hwanseok";
        Long categoryId=1L;
        Optional<Partner> partner = partnerRepository.findByAddress(address);
        Assertions.assertNotNull(partner);
    }

    @Test
    @Transactional
    public void update(){
        String address = "경기 시흥";
        String newAddress = "경기 시흥 신천동";
        Optional<Partner> partner = partnerRepository.findByAddress(address);
        Assertions.assertNotNull(partner);

        partner.ifPresent(selectedPartner -> {
            selectedPartner.setAddress(newAddress);
            Partner updatedPartner = partnerRepository.save(selectedPartner);

            Assertions.assertEquals(updatedPartner.getAddress(), newAddress);
        });
    }
    
    @Test
    @Transactional
    public void delete(){
        String address = "경기 시흥";
        String newAddress = "경기 시흥 신천동";
        Optional<Partner> partner = partnerRepository.findByAddress(address);
        Assertions.assertNotNull(partner);

        partner.ifPresent(selectedPartner -> {
            partnerRepository.delete(selectedPartner);
            Optional<Partner> deletedPartner = partnerRepository.findByAddress(address);
            Assertions.assertTrue(deletedPartner.isPresent());
        });

        String test = "test";
        Optional<String> optNotNull = Optional.of(test);  // 인자로 null 값을 받지 않음
        test = null;
        Optional<String> optCanBeNull = Optional.ofNullable(test); // 인자로 null 값을 받을 수 있음
        optNotNull.isPresent(); // null인지 체크
        optCanBeNull.isPresent(); // null인지 체크
    }
}
