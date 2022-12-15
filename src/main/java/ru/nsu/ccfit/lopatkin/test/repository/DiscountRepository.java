package ru.nsu.ccfit.lopatkin.test.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.test.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.test.entity.Discount;
import ru.nsu.ccfit.lopatkin.test.public_.tables.Discounts;
import ru.nsu.ccfit.lopatkin.test.public_.tables.Products;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiscountRepository implements CrudRepository<Discount>{

    private final DSLContext dsl;
    @Override
    public Discount insert(Discount discount) {
         return dsl.insertInto(Discounts.DISCOUNTS)
                .set(dsl.newRecord(Discounts.DISCOUNTS, discount))
                .returning()
                .fetchOne()
                .into(Discount.class);
    }

    @Override
    public Discount update(Discount discount) {
        return dsl.update(Discounts.DISCOUNTS)
                .set(dsl.newRecord(Discounts.DISCOUNTS, discount))
                .where(Discounts.DISCOUNTS.ID.eq(discount.getId()))
                .returning()
                .fetchOne()
                .into(Discount.class);
    }

    public Discount findByProductId(int productId) {
        try {
            return dsl.select()
                    .from(Discounts.DISCOUNTS)
                    .where(Discounts.DISCOUNTS.PRODUCT_ID.eq(productId))
                    .fetchOne()
                    .into(Discount.class);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    @Override
    public Discount find(int id) {
        return dsl.selectFrom(Discounts.DISCOUNTS)
                .where(Discounts.DISCOUNTS.ID.eq(id))
                .fetchAny()
                .into(Discount.class);
    }

    @Override
    public List<Discount> findAll(Condition condition) {
        return dsl.selectFrom(Discounts.DISCOUNTS)
                .where(condition)
                .fetch()
                .into(Discount.class);
    }


    @Override
    public Boolean delete(int id) {
        return dsl.deleteFrom(Discounts.DISCOUNTS)
                .where(Discounts.DISCOUNTS.ID.eq(id))
                .execute() == SUCCESS_CODE;
    }
}
