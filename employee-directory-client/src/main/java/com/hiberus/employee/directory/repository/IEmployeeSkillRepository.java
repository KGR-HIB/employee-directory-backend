package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeSkillEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Skill;

/**
 * Repository interface fro EmployeeSkill
 *
 * @author bcueva
 * @version 1.0
 */
public interface IEmployeeSkillRepository extends IQueryDslBaseRepository<EmployeeSkillEntity> {

    /**
     * Find all the skills of an employee
     *
     * @author bcueva
     * @param employeeId Id of employee
     * @return List of skills
     */
    List<Skill> findByEmployeeId(Integer employeeId);
}
