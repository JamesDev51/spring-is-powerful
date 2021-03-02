package me.hwanseok.hwanseok20210225.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderDetailStatus {
    CONFIRM(0, "확인", "주문 확인"),
    COMPLETE(1, "완료", "주문 처리 완료");
    private Integer id;
    private String title;
    private String description;
}
