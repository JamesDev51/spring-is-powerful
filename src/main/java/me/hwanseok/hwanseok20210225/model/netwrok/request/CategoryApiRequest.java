package me.hwanseok.hwanseok20210225.model.netwrok.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.model.enumClass.CategoryType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryApiRequest {
    private Long id;
    private CategoryType type;
    private String title;
}
