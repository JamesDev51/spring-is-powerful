package me.hwanseok.hwanseok20210225.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.entity.AdminUser;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.AdminUserApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.AdminUserApiResponse;
import me.hwanseok.hwanseok20210225.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    @Autowired
    private AdminUserRepository adminUserRepository;


    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .lastLoginAt(body.getLastLoginAt())
                .loginFailCount(body.getLoginFailCount())
                .passwordUpdatedAt(body.getPasswordUpdatedAt())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(null)
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        log.info("{}", adminUserRepository.findById(id));

        return adminUserRepository
                .findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();
        Optional<AdminUser> optional = adminUserRepository.findById(body.getId());

        return optional.map(adminUser -> {
            return adminUser.setAccount(body.getAccount())
                    .setPassword(body.getPassword())
                    .setStatus(body.getStatus())
                    .setRole(body.getRole())
                    .setLastLoginAt(body.getLastLoginAt())
                    .setLoginFailCount(body.getLoginFailCount())
                    .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
                    .setRegisteredAt(body.getRegisteredAt())
                    .setUnregisteredAt(body.getUnregisteredAt());
        }).map(adminUser-> adminUserRepository.save(adminUser))
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<AdminUser> optional = adminUserRepository.findById(id);

        return optional.map(user -> {
            adminUserRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser) {

        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();

        return Header.OK(adminUserApiResponse);
    }
}
