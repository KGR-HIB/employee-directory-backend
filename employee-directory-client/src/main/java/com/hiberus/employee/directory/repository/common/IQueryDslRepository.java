package com.hiberus.employee.directory.repository.common;

import java.io.Serializable;
import java.util.Collection;

public interface IQueryDslRepository<T> {

    void save(T obj);

    void saveAll(Collection<T> objs);

    void update(T obj);

    T findById(Serializable id);
}
