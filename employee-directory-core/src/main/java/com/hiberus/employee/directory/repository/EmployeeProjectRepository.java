package com.hiberus.employee.directory.repository;

import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.EmployeeProjectEntity;
import com.hiberus.employee.directory.entity.QEmployeeEntity;
import com.hiberus.employee.directory.entity.QEmployeeProjectEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.querydsl.core.BooleanBuilder;

/**
 * EmployeeProjectRepository.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Lazy
@Repository
public class EmployeeProjectRepository extends JPAQueryDslBaseRepository<EmployeeProjectEntity>
    implements IEmployeeProjectRepository {

    /**
     * Constructor.
     */
    public EmployeeProjectRepository() {
        super(EmployeeProjectEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(List<Integer> projectIds, Integer employeId, Integer createdByUser) {
        for (Integer projectId : projectIds) {
            EmployeeProjectEntity entity = new EmployeeProjectEntity();
            entity.setCreateDate(DateUtil.currentDate());
            entity.setCreatedByUser(createdByUser);
            entity.setProjectId(projectId);
            entity.setEmployeId(employeId);
            this.save(entity);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableEmployeeProject(List<Integer> projectIds, Integer employeId, Integer createdByUser) {
        /*QEmployeeProjectEntity  qEmployeeProjectEntity = QEmployeeProjectEntity.employeeProjectEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qEmployeeProjectEntity.employeId.eq(employeId));
        where.and(qEmployeeProjectEntity.status.eq(Boolean.TRUE));
        update(qEmployeeEntity).where(where).set(qEmployeeEntity.name, employeeEntity.getName())
            .set(qEmployeeEntity.lastName, employeeEntity.getLastName())
            .set(qEmployeeEntity.phone, employeeEntity.getPhone())
            .set(qEmployeeEntity.photoPath, employeeEntity.getPhotoPath())
            .set(qEmployeeEntity.cityId, employeeEntity.getCityId())
            .set(qEmployeeEntity.positionId, employeeEntity.getPositionId())
            .set(qEmployeeEntity.departmentId, employeeEntity.getDepartmentId())
            
            .set(qEmployeeProjectEntity.lastModifiedByUser, employeeEntity.getLastModifiedByUser())
            .set(qEmployeeEntity.lastModifiedDate, DateUtil.currentDate()).execute();*/

    }

}
