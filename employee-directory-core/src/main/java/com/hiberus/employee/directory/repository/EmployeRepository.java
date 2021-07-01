package com.hiberus.employee.directory.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;

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

}
