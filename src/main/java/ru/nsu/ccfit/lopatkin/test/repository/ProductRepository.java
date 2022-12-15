package ru.nsu.ccfit.lopatkin.test.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.test.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.test.entity.Product;
import ru.nsu.ccfit.lopatkin.test.public_.tables.Discounts;
import ru.nsu.ccfit.lopatkin.test.public_.tables.Products;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements CrudRepository<Product>{

    private final DSLContext dsl;

    @Override
    public Product insert(Product product) {
        return dsl.insertInto(Products.PRODUCTS)
                .set(dsl.newRecord(Products.PRODUCTS, product))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("error inserting entity: " + product.getName()))
                .into(Product.class);
    }

    @Override
    public Product update(Product product) {
        return dsl.update(Products.PRODUCTS)
                .set(dsl.newRecord(Products.PRODUCTS, product))
                .where(Products.PRODUCTS.ID.eq(product.getId()))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new DataAccessException("error updating entity: " + product.getName()))
                .into(Product.class);
    }

    public List<ProductDTO> findAll() {
        return dsl.select(Products.PRODUCTS.ID, Products.PRODUCTS.NAME, Products.PRODUCTS.PRICE, Discounts.DISCOUNTS.AMOUNT)
                .from(Products.PRODUCTS)
                .leftJoin(Discounts.DISCOUNTS)
                .on(Products.PRODUCTS.ID.eq(Discounts.DISCOUNTS.PRODUCT_ID))
                .fetch()
                .map(r -> new ProductDTO(r.component1(),
                        (String) r.component2(),
                        r.component3(),
                        r.component4() == null ? 0: r.component4(),
                        (int)(r.component4() == null ? r.component3() : r.component3() * ((100 - r.component4())/100.0))));
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public List<Product> findAll(Condition condition) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }
}
