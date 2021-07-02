package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeEntity.employeeEntity;
import static com.hiberus.employee.directory.entity.QUserEntity.userEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.Employe;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * EmployeRepository.java
 * 
 * @author Kruger on 01/07/2021
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
        this.save(employeeEntity);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateValues(EmployeeEntity employeeEntity) {
        // TODO Auto-generated method stub

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
