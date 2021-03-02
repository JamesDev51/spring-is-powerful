package me.hwanseok.hwanseok20210225.model.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"category", "itemList"})
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // user.setId() is not permitted
    private Long id;
    private String name;
    private String status;
    private String address;
    private String partnerNumber;
    private String businessNumber;
    private String ceoName;

    @ManyToOne
    private Category category;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Item> itemList;
}
