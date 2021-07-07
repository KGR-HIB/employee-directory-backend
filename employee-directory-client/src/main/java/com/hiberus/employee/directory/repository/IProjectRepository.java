package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Project;

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
    List<Project> findAll();

    /**
     * Create tag if it does not exist.
     * 
     * @author acachiguango on 02/07/2021
     * @param projects List ProjectEntity
     * @param createdByUser user Id
     * @return List projectIds
     */
    List<Integer> createByName(List<ProjectEntity> projects, Integer createdByUser);

    /**
     * Create project.
     * 
     * @author acachiguango on 02/07/2021
     * @param projectEntity ProjectEntity
     */
    void create(ProjectEntity projectEntity);

}
