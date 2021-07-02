package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeProjectEntity.employeeProjectEntity;
import static com.hiberus.employee.directory.entity.QProjectEntity.projectEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeProjectEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Project;
import com.querydsl.jpa.JPQLQuery;

/**
 * Repository for Projects of an employee
 *
 * @author bcueva
 * @version 1.0
 */
public class EmployeeProjectRepository extends JPAQueryDslBaseRepository<EmployeeProjectEntity> implements IEmployeeProjectRepository {

    public EmployeeProjectRepository() {
        super(EmployeeProjectEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> findByEmployeeId(Integer employeeId) {
        JPQLQuery<Project> jpqlQuery = from(employeeProjectEntity)
            .select(bean(Project.class, projectEntity.id, projectEntity.name))
            .join(employeeProjectEntity.project, projectEntity);

        jpqlQuery.where(
            projectEntity.status.eq(Boolean.TRUE)
                .and(employeeProjectEntity.status.eq(Boolean.TRUE))
                .and(employeeProjectEntity.employeeId.eq(employeeId))
        );

        return jpqlQuery.fetch();
    }
}
