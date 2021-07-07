package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.vo.Project;

/**
 * IEmployeeProjectService.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 */
public interface IEmployeeProjectService {
    /**
     * Create tag if it does not exist.
     * 
     * @author acachiguango on 02/07/2021
     * @param projects List ProjectEntity
     * @param employeeId employee Id
     * @param createdByUser user Id
     * @return List Project
     */
    List<Project> createByName(List<ProjectEntity> projects, Integer employeeId, Integer createdByUser);
}
