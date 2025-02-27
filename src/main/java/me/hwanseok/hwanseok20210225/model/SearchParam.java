package me.hwanseok.hwanseok20210225.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam {

    private String account;
    private String email;
    private int page;
}
