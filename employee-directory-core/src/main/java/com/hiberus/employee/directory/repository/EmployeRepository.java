package com.hiberus.employee.directory.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.entity.QEmployeeEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.querydsl.core.BooleanBuilder;

/**
 * EmployeRepository.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Lazy
@Repository
public class EmployeRepository extends JPAQueryDslBaseRepository<EmployeeEntity> implements IEmployeRepository {

    /**
     * Constructor.
     */
    public EmployeRepository() {
        super(EmployeeEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(EmployeeEntity employeeEntity) {
        employeeEntity.setCreateDate(DateUtil.currentDate());
        employeeEntity.setStatus(Boolean.TRUE);
        employeeEntity.setCity(null);
        employeeEntity.setPosition(null);
        employeeEntity.setDepartment(null);
        employeeEntity.setUser(null);
        this.save(employeeEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateValues(EmployeeEntity employeeEntity) {
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qEmployeeEntity.id.eq(employeeEntity.getId()));
        where.and(qEmployeeEntity.status.eq(Boolean.TRUE));
        update(qEmployeeEntity).where(where).set(qEmployeeEntity.name, employeeEntity.getName())
            .set(qEmployeeEntity.lastName, employeeEntity.getLastName())
            .set(qEmployeeEntity.phone, employeeEntity.getPhone())
            .set(qEmployeeEntity.photoPath, employeeEntity.getPhotoPath())
            .set(qEmployeeEntity.cityId, employeeEntity.getCityId())
            .set(qEmployeeEntity.positionId, employeeEntity.getPositionId())
            .set(qEmployeeEntity.departmentId, employeeEntity.getDepartmentId())
            .set(qEmployeeEntity.immediateChiefId, employeeEntity.getImmediateChiefId())
            .set(qEmployeeEntity.lastModifiedByUser, employeeEntity.getLastModifiedByUser())
            .set(qEmployeeEntity.lastModifiedDate, DateUtil.currentDate()).execute();
    }

}
