package ru.nsu.ccfit.lopatkin.test.repository;

import org.jooq.Condition;

import java.util.List;

public interface CrudRepository<T> {

    Integer SUCCESS_CODE = 1;

    T insert(T t);

    T update(T t);

    T find(int id);

    List<T> findAll(Condition condition);

    Boolean delete(int id);
}
