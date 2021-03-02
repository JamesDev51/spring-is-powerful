package me.hwanseok.hwanseok20210225.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderGroup","item"})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // user.setId() is not permitted
    private Long id;
    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private Integer totalPrice;

    @ManyToOne
    private Item item;

    @ManyToOne
    private OrderGroup orderGroup;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
