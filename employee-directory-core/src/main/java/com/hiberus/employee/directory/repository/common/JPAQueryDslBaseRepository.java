package com.hiberus.employee.directory.repository.common;

import java.io.Serializable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Repository base with support querydsl
 *
 * @author bcueva
 * @version 1.0
 * @param <T> Entity
 */
public abstract class JPAQueryDslBaseRepository<T> extends QuerydslRepositorySupport implements
    IQueryDslBaseRepository<T> {

    /**
     * Entity class
     */
    private final Class<T> domainClass;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public JPAQueryDslBaseRepository(Class<T> domainClass) {
        super(domainClass);
        this.domainClass = domainClass;
    }

    @Override
    public void save(T obj) {
        getEntityManager().persist(obj);
    }

    @Override
    public void update(T obj) {
        getEntityManager().merge(obj);
    }

    @Override
    public T findById(Serializable id) {
        return this.getEntityManager().find(this.domainClass, id);
    }
}
