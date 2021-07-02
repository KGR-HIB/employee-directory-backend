package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QProjectEntity.projectEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Project;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Project repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class ProjectRepository extends JPAQueryDslBaseRepository<ProjectEntity> implements IProjectRepository {

    public ProjectRepository() {
        super(ProjectEntity.class);
    }

    @Override
    public List<Project> findAll() {
        return from(projectEntity)
            .select(bean(Project.class, projectEntity.id, projectEntity.name))
            .where(projectEntity.status.eq(Boolean.TRUE))
            .fetch();
    }
}
