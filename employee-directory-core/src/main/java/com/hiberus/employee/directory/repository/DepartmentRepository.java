package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QDepartmentEntity.departmentEntity;

import java.util.List;
import com.hiberus.employee.directory.entity.DepartmentEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Department repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class DepartmentRepository extends JPAQueryDslBaseRepository<DepartmentEntity> implements IDepartmentRepository {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public DepartmentRepository() {
        super(DepartmentEntity.class);
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return from(departmentEntity)
            .where(departmentEntity.status.eq(Boolean.TRUE))
            .fetch();
    }
}
