package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeProjectEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Project;

/**
 * Repository for Projects of an employee
 *
 * @author bcueva
 * @version 1.0
 */
public interface IEmployeeProjectRepository extends IQueryDslBaseRepository<EmployeeProjectEntity> {

    /**
     * Get all projects of an employee
     *
     * @param employeeId Id of employee
     * @return List of projects
     */
    List<Project> findByEmployeeId(Integer employeeId);
}
