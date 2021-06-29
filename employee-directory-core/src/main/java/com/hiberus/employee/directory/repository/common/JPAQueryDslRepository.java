package com.hiberus.employee.directory.repository.common;

import java.util.Collection;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Repository base with support querydsl
 *
 * @author bcueva
 * @version 1.0
 * @param <T> Entity
 */
public abstract class JPAQueryDslRepository<T> extends QuerydslRepositorySupport implements IQueryDslRepository<T> {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public JPAQueryDslRepository(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public void save(T obj) {
        getEntityManager().persist(obj);
    }

    @Override
    public void saveAll(Collection<T> objs) {
        objs.forEach(obj -> getEntityManager().persist(obj));
    }

    @Override
    public void update(T obj) {
        getEntityManager().merge(obj);
    }
}
