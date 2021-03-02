package me.hwanseok.hwanseok20210225.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"partnerList"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // user.setId() is not permitted
    private Long id;
    private String type;
    private String title;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Partner> partnerList;
}
