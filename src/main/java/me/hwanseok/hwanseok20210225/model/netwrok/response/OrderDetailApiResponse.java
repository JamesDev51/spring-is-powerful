package me.hwanseok.hwanseok20210225.model.netwrok.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.model.enumClass.OrderDetailStatus;
import me.hwanseok.hwanseok20210225.model.enumClass.OrderGroupStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailApiResponse {
    private Long id;
    private OrderDetailStatus status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private Integer totalPrice;

    private Long itemId;
    private Long orderGroupId;
}
