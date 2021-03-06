package com.hiberus.employee.directory.service.common;

import java.io.Serializable;
import org.springframework.transaction.annotation.Transactional;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;

public class BaseService<T, R extends IQueryDslBaseRepository<T>> implements IBaseService<T> {

    protected final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(T obj) {
        this.repository.save(obj);
    }

    @Transactional
    @Override
    public void update(T obj) {
        this.repository.update(obj);
    }

    @Override
    public T findById(Serializable id) {
        return this.repository.findById(id);
    }
}
