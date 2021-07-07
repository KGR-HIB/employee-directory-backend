package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QProjectEntity.projectEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.util.NameUtil;
import com.hiberus.employee.directory.vo.Project;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> findAll() {
        return from(projectEntity).select(bean(Project.class, projectEntity.id, projectEntity.name))
            .where(projectEntity.status.eq(Boolean.TRUE)).fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> createByName(List<ProjectEntity> projects, Integer createdByUser) {
        List<Integer> projectIds = new ArrayList<>();
        if (CollectionUtils.isEmpty(projects)) {
            return projectIds;
        }
        for (ProjectEntity projectEntity : projects) {
            if (null == projectEntity.getId()) {
                projectIds.add(this.validateByName(projectEntity, createdByUser));
            } else {
                projectIds.add(projectEntity.getId());
            }
        }
        return projectIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(ProjectEntity projectEntity) {
        projectEntity.setName(NameUtil.clearAccents(projectEntity.getName()));
        projectEntity.setCreateDate(DateUtil.currentDate());
        projectEntity.setStatus(Boolean.TRUE);
        this.save(projectEntity);
    }

    /**
     * Validate by name.
     * 
     * @author acachiguango on 02/07/2021
     * @param projectEntity ProjectEntity
     * @param createdByUser userId
     * @return projectId
     */
    private Integer validateByName(ProjectEntity projectEntity, Integer createdByUser) {
        ProjectEntity entity = this.findByName(projectEntity.getName());
        if (null == entity) {
            projectEntity.setCreatedByUser(createdByUser);
            this.create(projectEntity);
            return projectEntity.getId();
        }
        return entity.getId();
    }

    /**
     * Get tag by name.
     * 
     * @author acachiguango on 01/07/2021
     * @param name tag name
     * @return ProjectEntity
     */
    private ProjectEntity findByName(String name) {
        String value = NameUtil.clearAccents(name);
        BooleanBuilder where = new BooleanBuilder();
        where.and(projectEntity.name.equalsIgnoreCase(value));
        where.and(projectEntity.status.eq(Boolean.TRUE));
        JPQLQuery<ProjectEntity> query = from(projectEntity).select(bean(ProjectEntity.class, projectEntity.id));
        query.where(where);
        return query.fetchFirst();
    }

}
