package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeProjectEntity.employeeProjectEntity;
import static com.hiberus.employee.directory.entity.QProjectEntity.projectEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.EmployeeProjectEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.Project;
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
        for (Integer projectId : projectIds) {
            EmployeeProjectEntity entity = new EmployeeProjectEntity();
            entity.setCreateDate(DateUtil.currentDate());
            entity.setCreatedByUser(createdByUser);
            entity.setProjectId(projectId);
            entity.setEmployeeId(employeeId);
            this.save(entity);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableEmployeeProject(List<Integer> projectIds, Integer employeeId, Integer createdByUser) {
        /*
         * QEmployeeProjectEntity qEmployeeProjectEntity =
         * QEmployeeProjectEntity.employeeProjectEntity; BooleanBuilder where = new
         * BooleanBuilder(); where.and(qEmployeeProjectEntity.employeId.eq(employeId));
         * where.and(qEmployeeProjectEntity.status.eq(Boolean.TRUE));
         * update(qEmployeeEntity).where(where).set(qEmployeeEntity.name,
         * employeeEntity.getName()) .set(qEmployeeEntity.lastName,
         * employeeEntity.getLastName()) .set(qEmployeeEntity.phone,
         * employeeEntity.getPhone()) .set(qEmployeeEntity.photoPath,
         * employeeEntity.getPhotoPath()) .set(qEmployeeEntity.cityId,
         * employeeEntity.getCityId()) .set(qEmployeeEntity.positionId,
         * employeeEntity.getPositionId()) .set(qEmployeeEntity.departmentId,
         * employeeEntity.getDepartmentId())
         * .set(qEmployeeProjectEntity.lastModifiedByUser,
         * employeeEntity.getLastModifiedByUser())
         * .set(qEmployeeEntity.lastModifiedDate, DateUtil.currentDate()).execute();
         */

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> findByEmployeeId(Integer employeeId) {
        JPQLQuery<Project> jpqlQuery =
            from(employeeProjectEntity).select(bean(Project.class, projectEntity.id, projectEntity.name))
                .join(employeeProjectEntity.project, projectEntity);
        jpqlQuery.where(projectEntity.status.eq(Boolean.TRUE).and(employeeProjectEntity.status.eq(Boolean.TRUE))
            .and(employeeProjectEntity.employeeId.eq(employeeId)));
        return jpqlQuery.fetch();
    }

}
