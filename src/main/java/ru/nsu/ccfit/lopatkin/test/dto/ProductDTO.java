package ru.nsu.ccfit.lopatkin.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;

    private int stockPrice;

    private int discount;

    private int endPrice;
}
