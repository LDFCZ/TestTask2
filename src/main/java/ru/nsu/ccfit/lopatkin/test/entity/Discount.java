package ru.nsu.ccfit.lopatkin.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    private Integer id;

    private Integer amount;

    private LocalDateTime dateOfStart;

    private Integer productId;

    public Discount(int amount, LocalDateTime dateOfStart, Integer productId) {
        this.amount = amount;
        this.dateOfStart = dateOfStart;
        this.productId = productId;
    }
}
