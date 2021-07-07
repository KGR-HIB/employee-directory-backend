package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.Project;

/**
 * Project service interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IProjectService extends IBaseService<ProjectEntity> {

    /**
     * Find all projects
     *
     * @return List of projects
     */
    List<Project> findAll();

    /**
     * Create tag if it does not exist.
     * 
     * @author acachiguango on 02/07/2021
     * @param projects List ProjectEntity
     * @param createdByUser userId
     */
    void createByName(List<ProjectEntity> projects, Integer createdByUser);
}
