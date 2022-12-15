package ru.nsu.ccfit.lopatkin.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RabbitDTO {

    private int id;

    private int amount;
}
