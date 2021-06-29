package com.hiberus.employee.directory.service.common;

import java.io.Serializable;
import javax.transaction.Transactional;
import com.hiberus.employee.directory.repository.common.IQueryDslRepository;

@Transactional
public class BaseService<T, R extends IQueryDslRepository<T>> implements IBaseService<T> {

    protected final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Override
    public void save(T obj) {
        this.repository.save(obj);
    }

    @Override
    public void update(T obj) {
        this.repository.update(obj);
    }

    @Override
    public T findById(Serializable id) {
        return this.repository.findById(id);
    }
}
