package me.hwanseok.hwanseok20210225.model.netwrok.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.model.enumClass.AdminUserStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiResponse {
    private Long id;
    private String account;
    private String password;
    private AdminUserStatus status;
    private String role;
    private LocalDateTime lastLoginAt;
    private Integer login_fail_count;
    private LocalDateTime passwordUpdatedAt;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
}
