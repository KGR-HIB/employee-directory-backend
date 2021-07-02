package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeEntity.employeeEntity;
import static com.hiberus.employee.directory.entity.QUserEntity.userEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.entity.QEmployeeEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.Employe;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employe> findByNamesAndEmail(String query) {
        JPQLQuery<Employe> jpaQuery =
            from(employeeEntity).select(bean(Employe.class,employeeEntity.id, employeeEntity.name, employeeEntity.lastName, userEntity.email))
            .join(employeeEntity.user, userEntity);

        jpaQuery.where(
            employeeEntity.status.eq(Boolean.TRUE)
                .and(
                    employeeEntity.name.containsIgnoreCase(query)
                        .or(employeeEntity.lastName.containsIgnoreCase(query))
                        .or(userEntity.email.containsIgnoreCase(query))
                )
            );

        jpaQuery.orderBy(employeeEntity.name.asc(), employeeEntity.lastName.asc());

        return jpaQuery.fetch();
    }

}
