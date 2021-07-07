package com.hiberus.employee.directory.repository.common;

import java.io.Serializable;

/**
 * Repository interface with querydsl
 *
 * @author bcueva
 * @version 1.0
 * @param <T> Entity
 */
public interface IQueryDslBaseRepository<T> {

    /**
     * Save entity
     *
     * @param obj Entity to save
     */
    void save(T obj);

    /**
     * Update entity
     *
     * @param obj
     */
    void update(T obj);

    /**
     * Find entity by Id
     *
     * @param id Id of entity
     * @return Entity instance
     */
    T findById(Serializable id);
}
