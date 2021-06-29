package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.DepartmentEntity;
import com.hiberus.employee.directory.service.common.IBaseService;

/**
 * Department service interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IDepartmentService extends IBaseService<DepartmentEntity> {

    /**
     * Find all departments
     *
     * @return List of departments
     */
    List<DepartmentEntity> findAll();
}
