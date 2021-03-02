package me.hwanseok.hwanseok20210225.model.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryType {
    CLOTHING(0, "옷", "옷"),
    MULTI_SHOP(1, "멀티샵", "멀티샵"),
    FOOD(2, "음식", "음식"),
    SPORTS(3, "운동", "운동"),
    SHOPPING_MALL(4, "쇼핑몰", "쇼핑몰"),
    DUTY_FREE(5, "면세점", "면세점"),
    BEAUTY(6, "뷰티", "뷰티"),
    COMPUTER(7, "컴퓨터", "컴퓨터");
    private Integer id;
    private String title;
    private String description;
}
