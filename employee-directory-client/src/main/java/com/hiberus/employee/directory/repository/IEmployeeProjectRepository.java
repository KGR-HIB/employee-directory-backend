package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeProjectEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Project;

/**
 * Repository for Projects of an employee.
 *
 * @author bcueva
 * @version 1.0
 */
public interface IEmployeeProjectRepository extends IQueryDslBaseRepository<EmployeeProjectEntity> {

    /**
     * Create employee tag.
     * 
     * @author acachiguango on 02/07/2021
     * @param projectIds project Ids
     * @param employeId employe Id
     * @param createdByUser user Id
     */
    void create(List<Integer> projectIds, Integer employeId, Integer createdByUser);

    /**
     * Disable project employee.
     * 
     * @author acachiguango on 02/07/2021
     * @param projectIds project Ids
     * @param employeId employe Id
     * @param createdByUser user Id
     */
    void disableEmployeeProject(List<Integer> projectIds, Integer employeId, Integer createdByUser);

    /**
     * Get all projects of an employee
     * 
     * @author bcueva on 02/07/2021
     * @param employeeId
     * @return List<Project>
     */
    List<Project> findByEmployeeId(Integer employeeId);
}
