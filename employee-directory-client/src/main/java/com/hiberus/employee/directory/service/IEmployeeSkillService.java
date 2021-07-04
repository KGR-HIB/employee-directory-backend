package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeSkillEntity;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.Skill;

/**
 * IEmployeeSkillService.
 * 
 * @author acachiguango on 03/07/2021
 * @version 1.0
 */
public interface IEmployeeSkillService extends IBaseService<EmployeeSkillEntity> {
    /**
     * Create tag if it does not exist.
     * 
     * @author acachiguango on 02/07/2021
     * @param skills List SkillEntity
     * @param employeeId employee Id
     * @param createdByUser user Id
     * @return List Project
     */
    List<Skill> createByName(List<SkillEntity> skills, Integer employeeId, Integer createdByUser);

}
