package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;

/**
 * Project repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IProjectRepository extends IQueryDslBaseRepository<ProjectEntity> {

    /**
     * Find all projects
     *
     * @return List of projects
     */
    List<ProjectEntity> findAll();
}
