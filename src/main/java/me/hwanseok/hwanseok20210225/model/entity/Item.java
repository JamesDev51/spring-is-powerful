package me.hwanseok.hwanseok20210225.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderDetailList", "partner"})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // user.setId() is not permitted
    private Long id;
    private String status;
    private String name;
    private String title;
    private String content;
    private Integer price;
    private String brandName;

    @ManyToOne
    private Partner partner;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
//     LAZY = SELECT * FROM item WHERE id = ?
//     EAGER = SELECT * FROM item LEFT OUTER JOIN user_item on item.id = user_item.item_id WHERE item.id = ?
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
