package me.hwanseok.hwanseok20210225.model.netwrok.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.model.enumClass.OrderGroupStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGroupApiRequest {
    private Long id;
    private OrderGroupStatus status;
    private String orderType;
    private String revAddress;
    private String revName;
    private String paymentType;
    private Integer totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    private Long userId;
}
