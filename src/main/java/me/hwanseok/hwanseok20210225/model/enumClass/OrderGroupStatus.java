package me.hwanseok.hwanseok20210225.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderGroupStatus {
    ALL(0, "일괄", "일관 주문"),
    EACH(1, "개별", "개별 주문");
    private Integer id;
    private String title;
    private String description;
}
