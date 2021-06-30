package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.service.common.IBaseService;

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
    List<ProjectEntity> findAll();
}
