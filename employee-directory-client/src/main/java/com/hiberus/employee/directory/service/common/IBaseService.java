package com.hiberus.employee.directory.service.common;

import java.io.Serializable;

/**
 * Base service interface
 *
 * @author bcueva
 * @version 1.0
 * @param <T>
 */
public interface IBaseService<T> {

    /**
     * Save entity
     *
     * @param obj Entity to save
     */
    void save(T obj);

    /**
     * Update entity
     *
     * @param obj Entity to update
     */
    void update(T obj);

    /**
     * Find entity by id
     * 
     * @param id Id of entity
     * @return Entity instance
     */
    T findById(Serializable id);
}
