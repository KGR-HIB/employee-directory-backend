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
     * @param status state
     * @return List of skills
     */
    List<Skill> findByEmployeeId(Integer employeeId, Boolean status);

    /**
     * Create employee tag.
     * 
     * @author acachiguango on 02/07/2021
     * @param skillIds skill Ids
     * @param employeId employee Id
     * @param createdByUser user Id
     */
    void create(List<Integer> skillIds, Integer employeeId, Integer createdByUser);

    /**
     * Updates the status of the employee skill.
     * 
     * @author acachiguango on 02/07/2021
     * @param skillIds skill Ids
     * @param employeeId employe Id
     * @param createdByUser user Id
     * @param status state
     */
    void updateStatusEmployeeSkill(List<Integer> skillIds, Integer employeeId, Integer createdByUser, Boolean status);
}
