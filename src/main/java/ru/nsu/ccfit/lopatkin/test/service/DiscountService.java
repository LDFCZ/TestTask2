package ru.nsu.ccfit.lopatkin.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.test.dto.RabbitDTO;
import ru.nsu.ccfit.lopatkin.test.entity.Discount;
import ru.nsu.ccfit.lopatkin.test.entity.Product;
import ru.nsu.ccfit.lopatkin.test.repository.DiscountRepository;

import java.time.LocalDateTime;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public void updateDiscount(RabbitDTO rabbitDTO) {
        Discount discount = discountRepository.findByProductId(rabbitDTO.getId());
        if (discount != null) {
            discount.setAmount(rabbitDTO.getAmount());
            discountRepository.update(discount);
            return;
        }
        discount = new Discount(rabbitDTO.getAmount(), LocalDateTime.now(), rabbitDTO.getId());
        discountRepository.insert(discount);
    }

}
