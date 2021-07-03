package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeProjectEntity.employeeProjectEntity;
import static com.hiberus.employee.directory.entity.QProjectEntity.projectEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.hiberus.employee.directory.entity.EmployeeProjectEntity;
import com.hiberus.employee.directory.entity.QEmployeeProjectEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.Project;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * Repository for Projects of an employee
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class EmployeeProjectRepository extends JPAQueryDslBaseRepository<EmployeeProjectEntity>
    implements IEmployeeProjectRepository {

    /**
     * EmployeeProjectRepository.
     */
    public EmployeeProjectRepository() {
        super(EmployeeProjectEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(List<Integer> projectIds, Integer employeeId, Integer createdByUser) {
        if (CollectionUtils.isEmpty(projectIds)) {
            this.updateStatusEmployeeProject(projectIds, employeeId, createdByUser, Boolean.FALSE);
            return;
        }
        List<Project> projects = this.findByEmployeeId(employeeId, null);
        List<Integer> create = new ArrayList<>();
        List<Integer> updateActive = new ArrayList<>();
        for (Integer projectId : projectIds) {
            Project project = projects.stream().filter(pro -> pro.getId().equals(projectId)).findFirst().orElse(null);
            if (null == project) {
                create.add(projectId);
            } else if (!project.getStatus()) {
                updateActive.add(project.getId());
            }
        }
        // Create tag relationship
        if (!CollectionUtils.isEmpty(create)) {
            for (Integer projectId : create) {
                EmployeeProjectEntity entity = new EmployeeProjectEntity();
                entity.setCreateDate(DateUtil.currentDate());
                entity.setCreatedByUser(createdByUser);
                entity.setEmployeeId(employeeId);
                entity.setProjectId(projectId);
                entity.setStatus(Boolean.TRUE);
                this.save(entity);
            }
        }
        // Update active tag status
        if (!CollectionUtils.isEmpty(updateActive)) {
            this.updateStatusEmployeeProject(updateActive, employeeId, createdByUser, Boolean.TRUE);
        }
        // Update inactive tag status
        List<Integer> updateInactive = projects.stream().filter(pro -> !projectIds.contains(pro.getId()))
            .map(Project::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(updateInactive)) {
            this.updateStatusEmployeeProject(updateInactive, employeeId, createdByUser, Boolean.FALSE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatusEmployeeProject(List<Integer> projectIds, Integer employeeId, Integer createdByUser,
        Boolean status) {
        QEmployeeProjectEntity qEmployeeProjectEntity = QEmployeeProjectEntity.employeeProjectEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qEmployeeProjectEntity.employeeId.eq(employeeId));
        if (!CollectionUtils.isEmpty(projectIds)) {
            where.and(qEmployeeProjectEntity.projectId.in(projectIds));
        }
        update(qEmployeeProjectEntity).where(where).set(qEmployeeProjectEntity.status, status)
            .set(qEmployeeProjectEntity.lastModifiedByUser, createdByUser)
            .set(qEmployeeProjectEntity.lastModifiedDate, DateUtil.currentDate()).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> findByEmployeeId(Integer employeeId, Boolean status) {
        JPQLQuery<Project> query = from(employeeProjectEntity)
            .select(bean(Project.class, projectEntity.id, projectEntity.name, employeeProjectEntity.status));
        query.join(employeeProjectEntity.project, projectEntity);
        BooleanBuilder where = new BooleanBuilder();
        where.and(employeeProjectEntity.employeeId.eq(employeeId));
        where.and(projectEntity.status.eq(Boolean.TRUE));
        if (null != status) {
            where.and(employeeProjectEntity.status.eq(status));
        }
        query.where(where);
        return query.fetch();
    }
}
