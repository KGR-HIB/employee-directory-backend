package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeEntity.employeeEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.entity.QCityEntity;
import com.hiberus.employee.directory.entity.QDepartmentEntity;
import com.hiberus.employee.directory.entity.QEmployeeEntity;
import com.hiberus.employee.directory.entity.QPositionEntity;
import com.hiberus.employee.directory.entity.QUserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.City;
import com.hiberus.employee.directory.vo.Department;
import com.hiberus.employee.directory.vo.Employe;
import com.hiberus.employee.directory.vo.Position;
import com.hiberus.employee.directory.vo.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * EmployeRepository.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 */
@Lazy
@Repository
public class EmployeeRepository extends JPAQueryDslBaseRepository<EmployeeEntity> implements IEmployeeRepository {

    /**
     * Constructor.
     */
    public EmployeeRepository() {
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
        QUserEntity qUserEntity = QUserEntity.userEntity;
        JPQLQuery<Employe> jpaQuery = from(employeeEntity)
            .select(bean(Employe.class,employeeEntity.id, employeeEntity.name, employeeEntity.lastName))
            .join(employeeEntity.user, qUserEntity);

        jpaQuery.where(
            employeeEntity.status.eq(Boolean.TRUE)
                .and(
                    employeeEntity.name.containsIgnoreCase(query)
                        .or(employeeEntity.lastName.containsIgnoreCase(query))
                        .or(qUserEntity.email.containsIgnoreCase(query))
                )
            );

        jpaQuery.orderBy(employeeEntity.name.asc(), employeeEntity.lastName.asc());

        return jpaQuery.fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employe findEmployeeMainInformationById(Integer id) {
        QCityEntity qCityEntity = QCityEntity.cityEntity;
        QDepartmentEntity qDepartmentEntity = QDepartmentEntity.departmentEntity;
        QPositionEntity qPositionEntity = QPositionEntity.positionEntity;
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QEmployeeEntity qEmployeeChief = new QEmployeeEntity("chief");

        JPQLQuery<Employe> jpqlQuery = from(employeeEntity)
            .select(bean(Employe.class, employeeEntity.id, employeeEntity.name, employeeEntity.lastName, employeeEntity.phone,
                bean(City.class, qCityEntity.id, qCityEntity.name).as("city"),
                bean(Department.class, qDepartmentEntity.id, qDepartmentEntity.name).as("department"),
                bean(Position.class, qPositionEntity.id, qPositionEntity.name).as("position"),
                bean(User.class, qUserEntity.id, qUserEntity.email).as("user"),
                bean(Employe.class, qEmployeeChief.id, qEmployeeChief.name, qEmployeeChief.lastName).as("immediateChief")
            ));

        jpqlQuery.leftJoin(employeeEntity.city, qCityEntity)
            .leftJoin(employeeEntity.department, qDepartmentEntity)
            .leftJoin(employeeEntity.position, qPositionEntity)
            .leftJoin(employeeEntity.user, qUserEntity)
            .leftJoin(qEmployeeChief).on(employeeEntity.immediateChiefId.eq(qEmployeeChief.id));

        jpqlQuery.where(
            employeeEntity.id.eq(id)
                .and(employeeEntity.status.eq(Boolean.TRUE))
        );

        return jpqlQuery.fetchFirst();
    }

}
