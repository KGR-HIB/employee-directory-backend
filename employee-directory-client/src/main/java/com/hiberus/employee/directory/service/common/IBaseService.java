package com.hiberus.employee.directory.service.common;

import java.io.Serializable;

public interface IBaseService<T> {

    void save(T obj);

    void update(T obj);

    T findById(Serializable id);

}
