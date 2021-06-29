package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.DepartmentEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;

/**
 * Department repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IDepartmentRepository extends IQueryDslBaseRepository<DepartmentEntity> {

    /**
     * Find all departments
     *
     * @return List of departments
     */
    List<DepartmentEntity> findAll();
}
